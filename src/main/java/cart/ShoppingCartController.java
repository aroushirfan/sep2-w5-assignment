package cart;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.text.MessageFormat;
import java.util.*;

public class ShoppingCartController {

    @FXML
    private VBox rootVBox;

    @FXML
    private Label titleLabel;
    @FXML
    private ComboBox<String> languageSelector;

    @FXML
    private Label itemsLabel;
    @FXML
    private TextField itemsField;
    @FXML
    private Button enterButton;

    @FXML
    private Label priceLabel;
    @FXML
    private TextField priceField;

    @FXML
    private Label quantityLabel;
    @FXML
    private TextField quantityField;

    @FXML
    private Button calculateButton;
    @FXML
    private Label totalLabel;

    private LocalizationService localizationService = new LocalizationService();
    private CartService cartService = new CartService();
    private static final String ENGLISH = "english";
    private Map<String, String> strings;
    private List<CartItem> itemsList = new ArrayList<>();

    private int totalItems = 0;
    private int currentItem = 1;
    private double totalCost = 0.0;

    private String currentLang = "en";

    @FXML
    public void initialize() {
        languageSelector.getItems().addAll(ENGLISH, "Arabic", "Finnish", "Swedish", "Japanese");
        languageSelector.getSelectionModel().select(ENGLISH);

        updateLanguage(ENGLISH);

        languageSelector.setOnAction(e -> updateLanguage(languageSelector.getValue()));
        enterButton.setOnAction(e -> handleEnter());
        calculateButton.setOnAction(e -> calculateTotal());

        showItemFields(false);
    }

    private void updateLanguage(String language) {

        currentLang = switch (language) {
            case "Finnish" -> "fi";
            case "Swedish" -> "sv";
            case "Japanese" -> "ja";
            case "Arabic" -> "ar";
            default -> "en";
        };

        strings = localizationService.loadLanguage(currentLang);

        titleLabel.setText(strings.get("title"));
        itemsLabel.setText(strings.get("prompt.noItems"));
        enterButton.setText(strings.get("button.enter"));
        calculateButton.setText(strings.get("button.calculate"));
        totalLabel.setText("");

        applyRTL();
    }

    private void applyRTL() {
        boolean rtl = currentLang.equals("ar");
        rootVBox.setNodeOrientation(rtl ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
    }

    @FXML
    public void handleEnter() {
        try {
            totalItems = Integer.parseInt(itemsField.getText());
            currentItem = 1;
            totalCost = 0.0;
            itemsList.clear();

            showItemFields(true);
            updateItemLabels();

        } catch (NumberFormatException e) {
            totalLabel.setText(strings.get("error.invalidInput"));
        }
    }

    @FXML
    public void calculateTotal() {
        try {
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            double subtotal = price * quantity;
            totalCost += subtotal;

            itemsList.add(new CartItem(currentItem, price, quantity, subtotal));

            currentItem++;

            if (currentItem <= totalItems) {
                updateItemLabels();
                priceField.clear();
                quantityField.clear();
                return;
            }

            totalLabel.setText(strings.get("label.total") + " " + totalCost);

            int cartId = cartService.saveCartRecord(totalItems, totalCost, currentLang);
            cartService.saveCartItems(cartId, itemsList);

        } catch (NumberFormatException e) {
            totalLabel.setText(strings.get("error.invalidInput"));
        }
    }

    private void updateItemLabels() {
        priceLabel.setText(MessageFormat.format(strings.get("prompt.priceItem"), currentItem));
        quantityLabel.setText(MessageFormat.format(strings.get("prompt.quantityItem"), currentItem));
    }

    private void showItemFields(boolean visible) {
        priceLabel.setVisible(visible);
        priceField.setVisible(visible);
        quantityLabel.setVisible(visible);
        quantityField.setVisible(visible);
        calculateButton.setVisible(visible);
    }
  // For testing – allow injecting mocks
  public void setLocalizationService(LocalizationService service) {
    this.localizationService = service;
  }

  public void setCartService(CartService service) {
    this.cartService = service;
  }

}
