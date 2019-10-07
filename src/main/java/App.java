import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Array;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/add", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/add", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String property = request.queryParams("property");
            String location = request.queryParams("location");
            String meansOfPayment = request.queryParams("meansOfPayment");
            Buy newBuy = new Buy(name, email, property, location,meansOfPayment);
            model.put("newBuy", newBuy);
            newBuy.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/add", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Buy> posts = Buy.all();
            model.put("posts", posts);

            return new ModelAndView(model, "list.hbs");
        }, new HandlebarsTemplateEngine());
        get("buyHouse", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "buyHouse.hbs");
        }, new HandlebarsTemplateEngine());

        get("/fill", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "reponseForm.hbs");
        }, new HandlebarsTemplateEngine());
        get("/land/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "lands-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/lands", ((request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "lands.hbs");
        }), new HandlebarsTemplateEngine());

        get("/succ", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "landsuccess.hbs");
        }, new HandlebarsTemplateEngine());




        post("lands", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String property = request.queryParams("property");
            String location = request.queryParams("location");
            String meansofpayment = request.queryParams("meansofpayment");
            String plot = request.queryParams("plot");
            String price = request.queryParams("price");
            String purpose = request.queryParams("purpose");
            String picture = request.queryParams("picture");

            Lands lands = new Lands(name, email, property, location, plot, meansofpayment, price, purpose, picture);
            lands.save();
            List<Lands> parcels = Lands.allLands();
            model.put("parcels", parcels);

            return new ModelAndView(model, "lands.hbs");
        }, new HandlebarsTemplateEngine());


    }
}