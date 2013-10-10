package controllers;

import com.google.common.io.Files;
import model.ProductModel;
import model.Tag;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.Catch;
import views.html.products.details;
import views.html.products.list;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Catch
public class Products extends Controller {
    private static final Form<ProductModel> productForm = Form
            .form(ProductModel.class);

    public static Result index() {
        return redirect(routes.Products.list(1));
    }

    public static Result list(Integer page) {
        Set<ProductModel> products = ProductModel.findAll();
        Html renderedTemplate = list.render(products);
        return ok(renderedTemplate);
    }

    public static Result newProduct() {
        return ok(details.render(productForm));
    }

    public static Result details(ProductModel product) {
        Form<ProductModel> filledForm = productForm.fill(product);
        return ok(details.render(filledForm));
    }

    public static Result save() {
        Form<ProductModel> boundForm = productForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        ProductModel product = boundForm.get();

        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart part = body.getFile("picture");
        if (part != null) {
            File picture = part.getFile();
            try {
                product.picture = Files.toByteArray(picture);
            } catch (IOException e) {
                return internalServerError("Error reading file upload");
            }
        }

        List<Tag> tags = new ArrayList<Tag>();
        for (Tag tag : product.tags) {
            if (tag.id != null) {
                tags.add(Tag.findById(tag.id));
            }
        }
        product.tags = tags;
        product.save();
        flash("success",
                String.format("Successfully added product %s", product));
        return redirect(routes.Products.list(1));
    }

    public static Result picture(String ean) {
        final ProductModel product = ProductModel.findByEan(ean);
        if (product == null) return notFound();
        return ok(product.picture);
    }
}
