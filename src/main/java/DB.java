import org.sql2o.Sql2o;

public class DB {
    private static String connectionString;
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/house", "wecode", "de los cobos");
}