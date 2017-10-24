package com.eblly.adapterPattern;


/**
 *
 * @author eblly
 * @date 2016年4月22日 上午10:04:22
 * @version 1.0
 */
/**
 * Adapter Pattern :有點像代理模式
 * 情景：需要給MP3擴展瀏覽txt功能。
 * @author eblly
 * @date Aug 13, 2015
 * @version 1.0
 **/
/**
 * 閱讀器
 */
interface TxtReader {
    void read(String bookPath);
}
/**
 * 本來有閱讀txt功能
 */
class AdobeReader implements TxtReader{
    @Override
    public void read(String bookPath) {
        System.out.println("看書..."+ bookPath);
    }
}
/**
 * txt適配器
 */
class TxtAdapter implements TxtReader{
    private TxtReader reader ;

    public  TxtAdapter() {
        reader = new AdobeReader();
    }

    @Override
    public void read(String bookPath) {
        reader.read(bookPath);
    }
}
/**
 * mp3播放器
 */
class MP3Player {
    private TxtAdapter adapter;

    public MP3Player(){
        adapter = new TxtAdapter();
    }

    public void play(String mp3Path) {
        System.out.println("唱歌..." + mp3Path);
    }

    public void readBook(String bookPath){
        adapter.read(bookPath);
    }
}

public class Main {
    public static void main(String[] args) {
        MP3Player mp3= new MP3Player();
        mp3.play("ddd");
        mp3.readBook("/home/三國演義.txt");
    }
}