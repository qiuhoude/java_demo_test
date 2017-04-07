package com.houde.lucene;

import java.io.File;
import java.io.FileFilter;

/**
 * 此类用于为 .txt 文件过滤器
 * Created by Administrator on 2017/3/30 0030.
 */
public class TextFileFilter implements FileFilter {

    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".txt");
    }
}
