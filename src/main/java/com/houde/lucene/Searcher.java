package com.houde.lucene;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class Searcher {

    IndexSearcher indexSearcher;
    QueryParser queryParser; //用来对查询语句进行语法分析
    Query query;
    IndexReader reader;

    public Searcher(String indexDirectoryPath)
            throws IOException {
        Directory indexDirectory =
                FSDirectory.open(new File(indexDirectoryPath));

        reader = DirectoryReader.open(indexDirectory);
        indexSearcher = new IndexSearcher(reader);
        queryParser = new QueryParser(Version.LUCENE_47,
                LuceneConstants.CONTENTS,
                new StandardAnalyzer(Version.LUCENE_47));
    }

    public TopDocs search(String searchQuery)
            throws IOException, ParseException {
        //调用parser 进行语法分析，形成查询语法树，放到Query 中
        query = queryParser.parse(searchQuery);
        int hitsPerPage = 10;
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        indexSearcher.search(query, collector);
        return collector.topDocs();
    }

    public Document getDocument(ScoreDoc scoreDoc)
            throws CorruptIndexException, IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }

    public void close() throws IOException {
        reader.close();
    }
}