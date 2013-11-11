package models;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 11.11.13
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */

import play.Logger;

public class Report {
    String name;

    public Report(String name) {
        this.name = name;
    }

    public void execute() {
        long start = System.currentTimeMillis();
        Logger.info("starting intensive " + name + " report at " + start);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        Logger.info("done with intensive " + name + " report "
        );
        Logger.info("took " + ((System.currentTimeMillis() - start) / 1000) + "s");
    }

    public String toString() {
        return name;
    }
}
