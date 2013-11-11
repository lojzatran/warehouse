package models;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 11.11.13
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
public class Order {

    public Order() {
    }

    private static String nextId() {
        Random random = new Random();
        return new BigInteger(30, random).toString(9);
    }

    public String toString() {
        return "Order " + nextId() + " date " + new Date() + "\r\n";
    }
}
