package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Lojza
 * Date: 18.10.13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Address {

    @Id
    public Long id;

    @OneToOne(mappedBy = "address")
    public Warehouse warehouse;

    public String street;
    public String number;
    public String postalCode;
    public String city;
    public String country;
}
