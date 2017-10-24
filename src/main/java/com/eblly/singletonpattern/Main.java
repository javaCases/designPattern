package com.eblly.singletonpattern;


/**
 * Singleton Pattern 情景：需要同一個類的情況下使用，例如一個swing的主界面只有一個。
 *
 * @author eblly
 * @date Aug 13, 2015
 * @version 1.0
 **/
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 靜態快初始化靜態類
 */
class Singleton2 {
    private static final Singleton2 instance;
    static {
        try {
            instance = new Singleton2();
        } catch (Exception e) {
            throw new RuntimeException("Darn, an error occurred!", e);
        }
    }

    public static Singleton2 getInstance() {
        return instance;
    }

    private Singleton2() {
    }

}


/**
 * 線程安全單例類 Thread-safe Singleton class.
 *
 */
public class Main {
    private static Main instance = null;

    private Main() {
    }

    public synchronized Main getInstance() {
		/*
		 * The instance gets created only when it is called for first time.
		 * Lazy-loading
		 */
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
}