package MP06.Ip.Richarte.Raul.Jouman.MP06.UF04.Java.Acces.Dades.PostgreSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/mp06_uf02_aea1";
    private static final String user = "postgres";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
