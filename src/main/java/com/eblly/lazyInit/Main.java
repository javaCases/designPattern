package com.eblly.lazyInit;

import java.util.HashMap;
import java.util.Map;


/**
 * Lazy initialization:當需要一個對象時再實例該對象，如果該對象已經存在則返回已存在的對象（類似單例類）
 * 情景：某些業務需要讀取某些配置（不需要啟動系統時讀取），如果該配置已經讀取了則直接從內存中獲取，不許要再重新讀取。
 * 現有文件系統的配置文檔、數據庫配置文檔等等。
 *
 * @author eblly
 * @version 1.0
 * @date Aug 12, 2015
 **/
enum CONFIGURE {
    DFS_CONF("文件系統配置文件"), SQL_CONF("sql保配置文件");
    String fileName;

    CONFIGURE(String fileName) {
        this.fileName = fileName;
    }

    public void printString() {
        System.out.println("我是:" + this.fileName);
    }
}

class ReadConfigure {
    private static Map<CONFIGURE, ReadConfigure> configureMap = new HashMap<CONFIGURE, ReadConfigure>();

    private ReadConfigure(CONFIGURE configure) {
    }

    /**
     * 無線程安全
     */
    public static ReadConfigure getReadConfigure(CONFIGURE configure) {
        ReadConfigure readConfigure;
        if (configureMap.get(configure) == null) {
            readConfigure = new ReadConfigure(configure);
            configureMap.put(configure, readConfigure);
        } else {
            readConfigure = configureMap.get(configure);
        }
        return readConfigure;
    }

    /**
     * 線程安全
     */
    public static ReadConfigure getReadConfigureConcurrentVersio(CONFIGURE configure) {
        if (configureMap.get(configure) == null) {
            synchronized (configure) {
                configureMap.put(configure, new ReadConfigure(configure));
            }
        }
        return configureMap.get(configure);
    }

    public void showMap() {
        if (configureMap.size() > 0) {
            System.out.println("Number of instances made = " + configureMap.size());
            for (Map.Entry<CONFIGURE, ReadConfigure> entry : configureMap.entrySet()) {
                System.out.println(entry.getKey().toString() + "===>" + entry.getKey().toString());
            }
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        ReadConfigure readConfigure = ReadConfigure.getReadConfigure(CONFIGURE.DFS_CONF);
        readConfigure.showMap();

        readConfigure = ReadConfigure.getReadConfigure(CONFIGURE.DFS_CONF);
        readConfigure.showMap();

        readConfigure = ReadConfigure.getReadConfigure(CONFIGURE.SQL_CONF);
        readConfigure.showMap();
    }
}