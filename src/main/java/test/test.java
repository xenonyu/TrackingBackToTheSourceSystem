package main.java.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class test {
    public static void main(String[] args) throws Exception {
        ComboPooledDataSource pool = new ComboPooledDataSource("demo");
        pool.getConnection();
        System.out.println(pool.getPassword());

    }

}
