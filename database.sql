-- Create database
CREATE DATABASE IF NOT EXISTS shopping_cart_localization
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE shopping_cart_localization;

-- Table: cart_records
CREATE TABLE IF NOT EXISTS cart_records (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            total_items INT NOT NULL,
                                            total_cost DOUBLE NOT NULL,
                                            language VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Table: cart_items
CREATE TABLE IF NOT EXISTS cart_items (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          cart_record_id INT,
                                          item_number INT NOT NULL,
                                          price DOUBLE NOT NULL,
                                          quantity INT NOT NULL,
                                          subtotal DOUBLE NOT NULL,
                                          FOREIGN KEY (cart_record_id) REFERENCES cart_records(id) ON DELETE CASCADE
    );

-- Table: localization_strings
CREATE TABLE IF NOT EXISTS localization_strings (
                                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                                    `key` VARCHAR(100) NOT NULL,
    value VARCHAR(255) NOT NULL,
    language VARCHAR(10) NOT NULL
    );

---------------------------------------------------------
-- INSERT LOCALIZATION STRINGS
---------------------------------------------------------

-- English (en)
INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'Shopping Cart', 'en'),
                                                              ('prompt.noItems', 'Enter the total number of items:', 'en'),
                                                              ('button.enter', 'Enter', 'en'),
                                                              ('prompt.priceItem', 'Enter the price of item {0}:', 'en'),
                                                              ('prompt.quantityItem', 'Enter the quantity of item {0}:', 'en'),
                                                              ('button.calculate', 'Calculate', 'en'),
                                                              ('label.total', 'The total cost of the items is:', 'en'),
                                                              ('error.invalidInput', 'Invalid input. Please enter numbers only.', 'en');

-- Arabic (ar)
INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'عربة التسوق', 'ar'),
                                                              ('prompt.noItems', 'أدخل عدد العناصر:', 'ar'),
                                                              ('button.enter', 'إدخال', 'ar'),
                                                              ('prompt.priceItem', 'أدخل سعر العنصر {0}:', 'ar'),
                                                              ('prompt.quantityItem', 'أدخل كمية العنصر {0}:', 'ar'),
                                                              ('button.calculate', 'احسب', 'ar'),
                                                              ('label.total', 'إجمالي تكلفة العناصر هو:', 'ar'),
                                                              ('error.invalidInput', 'إدخال غير صالح. الرجاء إدخال أرقام فقط.', 'ar');

-- Finnish (fi)
INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'Ostoskori', 'fi'),
                                                              ('prompt.noItems', 'Anna tuotteiden lukumäärä:', 'fi'),
                                                              ('button.enter', 'Syötä', 'fi'),
                                                              ('prompt.priceItem', 'Anna tuotteen {0} hinta:', 'fi'),
                                                              ('prompt.quantityItem', 'Anna tuotteen {0} määrä:', 'fi'),
                                                              ('button.calculate', 'Laske', 'fi'),
                                                              ('label.total', 'Tuotteiden kokonaishinta on:', 'fi'),
                                                              ('error.invalidInput', 'Virheellinen syöte. Syötä vain numeroita.', 'fi');

-- Swedish (sv)
INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'Kundvagn', 'sv'),
                                                              ('prompt.noItems', 'Ange antal artiklar:', 'sv'),
                                                              ('button.enter', 'Fortsätt', 'sv'),
                                                              ('prompt.priceItem', 'Ange pris för artikel {0}:', 'sv'),
                                                              ('prompt.quantityItem', 'Ange antal för artikel {0}:', 'sv'),
                                                              ('button.calculate', 'Beräkna', 'sv'),
                                                              ('label.total', 'Den totala kostnaden är:', 'sv'),
                                                              ('error.invalidInput', 'Ogiltig inmatning. Ange endast siffror.', 'sv');

-- Japanese (ja)
INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('title', 'ショッピングカート', 'ja'),
                                                              ('prompt.noItems', '商品の数を入力してください:', 'ja'),
                                                              ('button.enter', '入力', 'ja'),
                                                              ('prompt.priceItem', '商品 {0} の価格を入力してください:', 'ja'),
                                                              ('prompt.quantityItem', '商品 {0} の数量を入力してください:', 'ja'),
                                                              ('button.calculate', '計算', 'ja'),
                                                              ('label.total', '商品の合計金額は:', 'ja'),
                                                              ('error.invalidInput', '無効な入力です。数字のみ入力してください。', 'ja');