public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/shopping_cart_localization";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public static java.sql.Connection getConnection() {
        try {
            return java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
