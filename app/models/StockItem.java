package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class StockItem extends Model {
    @Id
    public Long id;

    @ManyToOne
    public Warehouse warehouse;

    @ManyToOne
    public ProductModel product;
    public Long quantity;
    public String toString() {
        return String.format("%d %s", quantity, product);
    }
}
