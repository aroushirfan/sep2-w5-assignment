import java.sql.*;
import java.util.List;

public class CartService {

    public int saveCartRecord(int totalItems, double totalCost, String language) {
        String sql = "INSERT INTO cart_records (total_items, total_cost, language) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, totalItems);
            stmt.setDouble(2, totalCost);
            stmt.setString(3, language);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void saveCartItems(int cartId, List<CartItem> items) {
        String sql = "INSERT INTO cart_items (cart_record_id, item_number, price, quantity, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (CartItem item : items) {
                stmt.setInt(1, cartId);
                stmt.setInt(2, item.getItemNumber());
                stmt.setDouble(3, item.getPrice());
                stmt.setInt(4, item.getQuantity());
                stmt.setDouble(5, item.getSubtotal());
                stmt.addBatch();
            }

            stmt.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
