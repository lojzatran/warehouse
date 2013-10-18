package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 16.10.13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Warehouse extends Model {

    @Id
    public Long id;

    public String name;

    @OneToMany(mappedBy = "warehouse")
    public List<StockItem> stock = new ArrayList<StockItem>();

    @OneToOne
    public Address address;

    public String toString() {
        return name;
    }
}
