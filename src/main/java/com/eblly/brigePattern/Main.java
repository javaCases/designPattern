package com.eblly.brigePattern;

/**
 * Bridge Pattern 情景：文本閱讀器
 * 类似Abstract Factory
 *
 * @author eblly
 * @version 1.0
 * @date Aug 13, 2015
 **/// ============ReaderAPI====================
interface ReaderAPI {
    void read(String path);
}

class TxtAPI implements ReaderAPI {
    @Override
    public void read(String path) {
        System.out.println("TxtAPI正在打開：" + path);
    }
}

class PdfAPI implements ReaderAPI {
    @Override
    public void read(String path) {
        System.out.println("PdfAPI正在打開：" + path);
    }

}

// ============Reader=================
abstract class Reader {
    protected ReaderAPI readerAPI;

    public Reader(ReaderAPI readerAPI) {
        this.readerAPI = readerAPI;
    }

    public abstract void read(String path);
}

class BookReader extends Reader {
    public BookReader(ReaderAPI readerAPI) {
        super(readerAPI);
    }

    @Override
    public void read(String path) {
        readerAPI.read(path);
    }

}

// ============App==================
public class Main {
    public static void main(String[] args) {
        Reader txtReader = new BookReader(new TxtAPI());
        txtReader.read("/home/三國演義.txt");

        Reader pdfReader = new BookReader(new PdfAPI());
        pdfReader.read("/home/三國演義.pdf");
    }
}