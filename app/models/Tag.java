package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.*;

@Entity
public class Tag extends Model {

    @Id
    public Long id;
    @Constraints.Required
    public String name;
    @ManyToMany(mappedBy = "tags")
    public List<ProductModel> products;

    public Tag() {
    }

    public Tag(Long id, String name, Collection<ProductModel> products) {
        this.id = id;
        this.name = name;
        this.products = new LinkedList<ProductModel>(products);
        for (ProductModel product : products) {
            product.tags.add(this);
        }
    }

    private static List<Tag> tags = new LinkedList<Tag>();

    static {
        tags.add(new Tag(1L, "lightweight",
                ProductModel.findByName("paperclips")));
        tags.add(new Tag(2L, "metal",
                ProductModel.findByName("paperclips")));
        tags.add(new Tag(3L, "plastic",
                ProductModel.findByName("paperclips")));
    }

    public static Finder<Long, Tag> find() {
        return new Finder<Long, Tag>(Long.class, Tag.class);
    }

    public static Tag findById(Long id) {
        return find().byId(id);
    }
}