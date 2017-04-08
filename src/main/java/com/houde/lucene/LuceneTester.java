package com.houde.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class LuceneTester {
    String indexDir = "E:/Lucene/Index";
    String dataDir = "C:\\Users\\Administrator\\Desktop\\note";
    Indexer indexer;
    Searcher searcher;

    public static void main(String[] args) {
        LuceneTester tester;
        try {
            tester = new LuceneTester();
//            tester.createIndex();
            tester.search("APACHE");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void createIndex() throws IOException{
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed+" File indexed, time taken: "
                +(endTime-startTime)+" ms");
    }

    private void search(String searchQuery) throws IOException, ParseException{
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs topDocs = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();
        ScoreDoc[] hits =topDocs.scoreDocs;
        System.out.println(hits.length +
                " documents found. Time :" + (endTime - startTime));
        for(ScoreDoc scoreDoc : hits) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: "
                    + doc.get(LuceneConstants.FILE_PATH) +" filename"+doc.get(LuceneConstants.FILE_NAME));
        }
        searcher.close();
    }
}
