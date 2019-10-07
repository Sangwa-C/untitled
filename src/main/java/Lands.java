import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.swing.*;
import java.util.List;


public class Lands {
    private String name;
    private String email;
    private String property;
    private String location;
    private String meansofpayment;
    private String plot;
    private String price;
    private String purpose;
    private String picture;
    private int id;

    public Lands(String name, String email, String property, String location, String meansofpayment, String plot, String price, String purpose, String picture) {
        this.name = name;
        this.email = email;
        this.property = property;
        this.location = location;
        this.meansofpayment = meansofpayment;
        this.plot = plot;
        this.price = price;
        this.purpose = purpose;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMeansofpayment() {
        return meansofpayment;
    }

    public void setMeansofpayment(String meansofpayment) {
        this.meansofpayment = meansofpayment;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save(){
        try (Connection con =  DB.sql2o.open()){
            String sql = "INSERT INTO lands (name, email, property, location, meansofpayment, plot, price, purpose, picture) VALUES (:name, :email, :property, :location, :meansofpayment, :plot, :price, :purpose, :picture)";
            this.id =(int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("property", this.property)
                    .addParameter("location", this.location)
                    .addParameter("meansofpayment", this.meansofpayment)
                    .addParameter("plot", this.plot)
                    .addParameter("price", this.price)
                    .addParameter("purpose", this.purpose)
                    .addParameter("picture", this.picture)
                    .executeUpdate()
                    .getKey();
        }

    }

    public static List<Lands> allLands(){
        String sql = "SELECT * FROM lands;";
        try (Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Lands.class);
        }
    }
    public static List<Lands> search(){
        String sql = "SELECT * FROM lands WHERE location = search;";
        try (Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Lands.class);
        }
    }
}
