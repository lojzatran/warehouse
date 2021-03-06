package models;

import play.db.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 29.9.13
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ProductModel extends Model {


    private static Set<ProductModel> products;


    static {
        products = new HashSet<ProductModel>();
        products.add(new ProductModel("1111111111111", "Paperclips 1",
                "Paperclips description 1"));
        products.add(new ProductModel("2222222222222", "Paperclips 2",
                "Paperclips description "));
        products.add(new ProductModel("3333333333333", "Paperclips 3",
                "Paperclips description 3"));
        products.add(new ProductModel("4444444444444", "Paperclips 4",
                "Paperclips description 4"));
        products.add(new ProductModel("5555555555555", "Paperclips 5",
                "Paperclips description 5"));
    }

    @Id
    public Long id;

    @ManyToMany
    public List<Tag> tags = new LinkedList<Tag>();
    @Constraints.Required
    public String ean;
    @Constraints.Required
    public String name;
    public String description;
    public byte[] picture;

    @OneToMany(mappedBy = "product")
    public List<StockItem> stockItems;

    public ProductModel() {
    }

    public ProductModel(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public static Set<ProductModel> findAll() {
        return new HashSet<ProductModel>(products);
    }

    public static ProductModel findByEan(String ean) {
        for (ProductModel candidate : products) {
            if (candidate.ean.equals(ean)) {
                return candidate;
            }
        }
        return null;
    }

    public static Set<ProductModel> findByName(String term) {
        final Set<ProductModel> results = new HashSet<ProductModel>();
        for (ProductModel candidate : products) {
            if (candidate.name.toLowerCase().contains(term.toLowerCase())) {
                results.add(candidate);
            }
        }
        return results;
    }

    public static boolean remove(ProductModel product) {
        return products.remove(product);
    }

    public String toString() {
        return String.format("%s - %s", ean, name);
    }

    public static Finder<Long, ProductModel> find() {
        return new Finder<Long, ProductModel>(Long.class, ProductModel.class);
    }

}
