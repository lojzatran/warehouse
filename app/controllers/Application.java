package controllers;

import models.ExpeditedOrders;
import play.libs.Comet;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.WebSocket;
import views.html.index;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 11.11.13
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class Application extends Controller {

    public static WebSocket<String> liveUpdate() {
        return new WebSocket<String>() {

            @Override
            public void onReady(final In<String> in, final Out<String> out) {
                // For each event received on the socket,
                in.onMessage(new F.Callback<String>() {
                    public void invoke(String event) {
                        ExpeditedOrders.notifyOthers(out, event
                                + " is being processed");
                    }
                });

                // When the socket is closed.
                in.onClose(new F.Callback0() {
                    public void invoke() {
                        ExpeditedOrders.unregister(out);
                    }
                });

                ExpeditedOrders.register(out);
            }
        };
    }

    public static Result index() {
        return ok(index.render("live stream"));
    }
}
