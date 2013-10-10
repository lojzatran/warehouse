package controllers;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.SimpleResult;
import utils.Catch;
import utils.ExceptionMailer;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 6.10.13
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */
public class CatchAction extends Action<Catch> {

    public F.Promise<SimpleResult> call(Http.Context ctx) {
        try {
            return delegate.call(ctx);
        } catch (Throwable e) {
            if (configuration.send()) {
                ExceptionMailer.send(e);
            } else {
                e.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }
}
