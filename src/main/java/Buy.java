import org.sql2o.Connection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Buy {
    private String name;
    private String email;
    private String property;
    private String location;
    private String meansOfPayment;
    private int price;
    private int id;

    public Buy(String name, String email, String property, String location, String meansOfPayment) {
        this.name=name;
        this.email =email;
        this.property=property;
        this.location=location;
        this.meansOfPayment= meansOfPayment;


    }
    public String getName() {

        return name;
    }
    public String getEmail() {

        return email;
    }
    public String getProperty() {

        return property;
    }
    public String getMeansOfPayment() {

        return meansOfPayment;
    }
    public int getPrice() {

        return price;
    }
    public  int getId() {
        return id;
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO buy (name,email,property,location,meansOfPayment) VALUES (:name,:email,:property,:location,:meansOfPayment);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("property", this.property)
                    .addParameter("location", this.location)
                    .addParameter("meansOfPayment", this.meansOfPayment)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Buy> all() {

        String sql = "select * from buy;";
        try(Connection con = DB.sql2o.open()) {

            return con.createQuery(sql).executeAndFetch(Buy.class);
        }
    }
}