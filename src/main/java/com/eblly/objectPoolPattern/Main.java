package com.eblly.objectPoolPattern;

import java.util.HashSet;
import java.util.Set;

/**
 * object pool Pattern
 * 情景：需要將資源回收，等待下次使用而不用創建對象。
 *
 * @author eblly
 * @version 1.0
 * @date Aug 12, 2015
 **/
abstract class ObjectPool<T> {

    private Set<T> available = new HashSet<>();
    private Set<T> inuse = new HashSet<>();

    abstract T create();

    public synchronized T checkOut() {
        if (available.size() <= 0) {
            available.add(create());
        }
        T instance = available.iterator().next();

        available.remove(instance);
        inuse.add(instance);

        return instance;
    }

    public synchronized void checkIn(T instance) {
        inuse.remove(instance);
        available.add(instance);
    }

    public String toString() {
        return String.format("Pool available=%d inUse=%d", available.size(), inuse.size());
    }
}

/**
 * session實體
 */
class Session {
    private static int count = 1;
    private final int id;

    public Session() {
        id = count++;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return ("session id = " + this.id);
    }

}

class SessionPool extends ObjectPool<Session> {

    @Override
    Session create() {
        return new Session();
    }

}

public class Main {

    public static void main(String[] args) {
        SessionPool pool = new SessionPool();
        Session session = pool.checkOut();
        System.out.println(session.toString());
        System.out.println(pool.toString());

        System.out.println();
        SessionPool pool2 = new SessionPool();
        Session session2 = pool2.checkOut();
        System.out.println(session2.toString());
        System.out.println(pool2.toString());

        System.out.println();
        SessionPool pool3 = new SessionPool();
        Session session3 = pool3.checkOut();
        System.out.println(session3.toString());
        System.out.println(pool3.toString());

        System.out.println();
        System.out.println("Checking in " + session2);
        pool.checkIn(session2);
        System.out.println("Checking in " + session3);
        pool.checkIn(session3);
        System.out.println(pool);

        System.out.println();
        Session session4 = pool.checkOut();
        System.out.println("Checked out " + session4);
        Session session5 = pool.checkOut();
        System.out.println("Checked out " + session5);
        System.out.println(pool);
    }
}