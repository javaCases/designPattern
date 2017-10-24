package com.eblly.multiplePattern;


import java.util.HashMap;
import java.util.Map;

/**
 * Multiton pattern
 * 情景：當連接數據庫時，session未關閉時則應該繼續使用當前session，而不則另外創建，這樣可以提高性能，節省內存開銷。
 *
 * @author eblly
 * @version 1.0
 * @date Aug 12, 2015
 **/
class SessionFactory {
    public static final Map<String, String> sessionMap = new HashMap<>();

    /**
     * 控制併發，還有一種是使用ConcurrentHashMap
     *
     * @param key
     * @return
     */
    public String getSession(String key) {
        synchronized (key) {
            String session = null;
            if ((session = sessionMap.get(key)) == null) {
                session = "new Session";
                sessionMap.put(key, session);
            }
            return session;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new SessionFactory();
        sessionFactory.getSession("111");
        System.out.println("sessionMap size=:" + sessionFactory.sessionMap.size());
        System.out.println();

        sessionFactory.getSession("111");
        System.out.println("sessionMap size=:" + sessionFactory.sessionMap.size());
        System.out.println();

        sessionFactory.getSession("222");
        System.out.println("sessionMap size=:" + sessionFactory.sessionMap.size());
        System.out.println();
    }
}