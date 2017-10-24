package com.eblly.resourceAcqurePattern;

/**
 * Resource Acquisition Is Initialization:
 * 情景：當需要資源就去讀取，用完之後關閉時自動執行close方法
 *
 * @author eblly
 * @version 1.0
 * @date Aug 13, 2015 1:12:06 AM
 **/
class TxtFile implements AutoCloseable {

    public TxtFile() {
        System.out.println("打開txt文件!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("關閉txt文件!");
    }
}

class ExcelFile implements AutoCloseable {

    public ExcelFile() {
        System.out.println("打開excel文件!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("關閉excel文件!");
    }

}

public class Main {
    public static void main(String[] args) throws Exception {

        try (TxtFile txtFile = new TxtFile()) {
            System.out.println("讀取中");
        }
        System.out.println("======================");
        try (ExcelFile excelFile = new ExcelFile();) {
            System.out.println("讀取中");
        }
    }
}
