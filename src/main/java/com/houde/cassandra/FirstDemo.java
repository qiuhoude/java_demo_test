package com.houde.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.reflect.TypeToken;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class FirstDemo {


    static String[] CONTACT_POINTS = {"192.168.123.128"};
    static int PORT = 9042;


    private Cluster cluster;
    private Session session;

    public static void main(String[] args) {

        FirstDemo client = new FirstDemo();
        try {
            client.connect(CONTACT_POINTS, PORT);
            client.createSchema();
            client.loadData();
            client.querySchema();
//            client.deleteData();
        } finally {
            client.close();
        }
    }


    /**
     * Initiates a connection to the cluster
     * specified by the given contact point. <br>
     * 连接到指定的Cassandra节点。 该节点最好是Seeds server
     *
     * @param contactPoints the contact points to use. 连接点
     * @param port          the port to use. 端口，默认9042
     */
    public void connect(String[] contactPoints, int port) {
        //加上数据库连接池
        // 数据库连接池选项
        PoolingOptions poolingOptions = new PoolingOptions();
        poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL, 4)
                .setMaxConnectionsPerHost(HostDistance.LOCAL, 10)
                .setCoreConnectionsPerHost(HostDistance.REMOTE, 2)
                .setMaxConnectionsPerHost(HostDistance.REMOTE, 4);
        cluster = Cluster.builder()
                // 配置数据库连接池
//                .withPoolingOptions(poolingOptions)
                .addContactPoints(contactPoints)
                .withPort(port)
                .withCredentials("qiu", "qiu123")
                .build();


        Metadata metadata = cluster.getMetadata();

        System.out.println(cluster.getMetadata().getAllHosts());
        for (KeyspaceMetadata keyspaceMetadata : metadata.getKeyspaces()) {
            System.out.println("键空间 : " + keyspaceMetadata.getName());
        }
        session = cluster.connect();
    }

    /**
     * Creates the schema (keyspace) and tables
     * for this example.
     */
    public void createSchema() {
        // 创建Keyspace simplex, 如果之前已经创建了就直接复用
        // 使用SimpleStrategy， 复制因子=1 （数据没有备份，只存放1份）
        session.execute("CREATE KEYSPACE IF NOT EXISTS simplex WITH replication " +
                "= {'class':'SimpleStrategy', 'replication_factor':1};");

        // 创建Table simplex.songs
        session.execute(
                "CREATE TABLE IF NOT EXISTS simplex.songs (" +
                        "id uuid PRIMARY KEY," +
                        "title text," +
                        "album text," +
                        "artist varchar," +    // Cassandra 之中varchar == text
                        "tags set<text>," +        // 这里用了set的数据类型
                        "data blob," +            // 二进制类型
                        "kv map<text,text>," +
                        "likes list<text>"+
                        ");");

        // 创建表simplex.playlists
        // 注意：playlists 跟 songs的数据是重复的，
        // 只是primary key 不太一样。
        // 这就是NoSQL跟RDBMS的不一样： 反范式
        session.execute(
                "CREATE TABLE IF NOT EXISTS simplex.playlists (" +
                        "id uuid," +
                        "title text," +
                        "album text, " +
                        "artist text," +
                        "song_id uuid," +
                        "PRIMARY KEY (id, title, album, artist)" +
                        ");");
    }

    /**
     * Inserts data into the tables.<br>
     * 插入数据
     */
    public void loadData() {

        session.execute(
                "INSERT INTO simplex.songs (id, title, album, artist, tags, kv, likes) " +
                        "VALUES (" +
                        "756716f7-2e54-4715-9f00-91dcbea6cf50," +
                        "'La Petite Tonkinoise'," +
                        "'Bye Bye Blackbird'," +
                        "'Joséphine Baker'," +
                        "{'jazz', '2013'}," +
                        "{'name':'houde','age':'26','name':'houde'}," +
                        "['look book','by bike','running'])" +
                        ";");

        session.execute(
                "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
                        "VALUES (" +
                        "2cc9ccb7-6221-4ccb-8387-f22b6a1b354d," +
                        "756716f7-2e54-4715-9f00-91dcbea6cf50," +
                        "'La Petite Tonkinoise'," +
                        "'Bye Bye Blackbird'," +
                        "'Joséphine Baker'" +
                        ");");
    }

    /**
     * Queries and displays data.<br>
     * 查询数据
     */
    public void querySchema() {

        ResultSet results = session.execute(
                "SELECT * FROM simplex.playlists " +
                        "WHERE id = 2cc9ccb7-6221-4ccb-8387-f22b6a1b354d;");

        System.out.printf("%-30s\t%-20s\t%-20s%n", "title", "album", "artist");
        System.out.println("-------------------------------+-----------------------+--------------------");

        for (Row row : results) {

            System.out.printf("%-30s\t%-20s\t%-20s%n",
                    row.getString("title"),
                    row.getString("album"),
                    row.getString("artist"));

        }


        List<Row> rows = session.execute(
                "SELECT * FROM simplex.songs ").all();
        TypeToken<Set<String>> typeToken = new TypeToken<Set<String>>() {
        };

        for (Row row : rows) {
            Set<String> tags = row.getSet("tags", String.class);
            System.out.println("set 集合" + tags);

            Map<String,String> kv = row.getMap("kv", String.class,String.class);
            System.out.println("map 集合" + kv);

            List<String> likes = row.getList("likes", String.class);
            System.out.println("list 集合" + likes);
        }
    }

    /**
     * Delete data <br>
     * 删除数据
     */
    private void deleteData() {
        Delete.Where delete = QueryBuilder.delete().from("simplex", "playlists")
                .where(QueryBuilder
                        .eq("id", UUID.fromString("2cc9ccb7-6221-4ccb-8387-f22b6a1b354d")));
        session.execute(delete);
//        ResultSet results = session.execute(
//                "delete from simplex.playlists " +
//                        "WHERE id = 2cc9ccb7-6221-4ccb-8387-f22b6a1b354d;");
        // 删除之后再次进行查询
        querySchema();
    }

    /**
     * Closes the session and the cluster.<br>
     * 最后一定要记得关闭！
     */
    public void close() {
        session.close();
        cluster.close();
    }
}
