package com.houde.spring.resource;

import org.springframework.core.io.Resource;

import java.util.*;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
public class ResourceBean3 {
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }


    static class Weighted {
        public Weighted(String name, int weighted) {
            this.name = name;
            this.weighted = weighted;
        }

        @Override
        public String toString() {
            return "Weighted{" +
                    "name='" + name + '\'' +
                    ", weighted=" + weighted +
                    '}';
        }

        public String name;
        public int weighted;
    }

    public static Weighted random(List<Weighted> list) {
//        System.out.println("加权前的list  "+list.size());
        //得出加权的后的list
        List<Weighted> weightedList = new ArrayList<Weighted>();
        for (Weighted bean : list) {
            int weighted = bean.weighted;
            while (weighted >= 0) {
                weightedList.add(bean);
                weighted--;
            }
        }
//        System.out.println("加权后的list  "+weightedList.size());
        Random rand = new Random();

        int index = rand.nextInt(weightedList.size());
//        System.out.println(index);
        return weightedList.get(index);
    }

    public static void main(String[] args) {
        Map<String, Integer> q = new HashMap<String, Integer>();
        List<Weighted> list = new ArrayList<Weighted>();
        list.add(new Weighted("大王", 9));
        list.add(new Weighted("小王", 8));
        list.add(new Weighted("小2", 5));
        list.add(new Weighted("A", 4));
        list.add(new Weighted("K", 3));
        list.add(new Weighted("Q", 1));
        for (int i = 0; i < 3600; i++) {
            Weighted w = random(list);
            if (q.get(w.name) == null) {
                q.put(w.name, 1);
            } else {
                q.put(w.name, q.get(w.name) + 1);
            }
        }
        for (Map.Entry<String, Integer> kv : q.entrySet()) {
            System.out.println(kv.getKey() + " 次数 = " + kv.getValue().intValue());
        }


    }
}
