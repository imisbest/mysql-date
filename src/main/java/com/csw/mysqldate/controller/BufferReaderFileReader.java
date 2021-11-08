package com.csw.mysqldate.controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderFileReader {


    public static void main(String[] args) throws IOException {
        String path = "E:\\IdeaProjects\\202107Codeing\\mysql-date\\src\\main\\resources\\file";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }


}
