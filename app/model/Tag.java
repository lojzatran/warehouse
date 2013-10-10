package model;

import play.data.validation.Constraints;

import java.util.*;

public class Tag {
    public Long id;
    @Constraints.Required
    public String name;
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
    public static Tag findById(Long id) {
        for (Tag tag : tags) {
            if(tag.id == id) return tag;
        }
        return null;
    }
}