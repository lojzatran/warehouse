package utils;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 6.10.13
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionMailer {

    public static void send(Throwable e) {
        System.out.println("Sending email containing exception " + e);
    }
}
