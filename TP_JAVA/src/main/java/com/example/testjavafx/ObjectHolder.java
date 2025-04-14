package com.example.testjavafx;

public final class ObjectHolder {

    private Object user;
    private final static ObjectHolder INSTANCE = new ObjectHolder();

    private ObjectHolder() {}

    public static ObjectHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(Object u) {
        this.user = u;
    }

    public Object getUser() {
        return this.user;
    }
}
