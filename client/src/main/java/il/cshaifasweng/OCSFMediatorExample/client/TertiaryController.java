package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TertiaryController {
    @FXML
    private TextField DishName;

    @FXML
    private TextArea Ingredients;

    @FXML
    private TextArea Preferences;

    @FXML
    private TextField Price;

    @FXML
    private Button SaveBtn;

    @FXML
    private Button BackBtn;


    Dish dish;

    @FXML
    void SaveChange(ActionEvent event) {
        try {
            int newPrice = Integer.parseInt(Price.getText());
            if (newPrice < 0) {
                Price.setText("Enter a positive number");
            } else {
                dish.price = newPrice;
                boolean sent = false;
                while (!sent) {
                    try {
                        SimpleClient.getClient().sendToServer(dish);
                        sent = true;
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        catch (NumberFormatException e) {
            Price.setText("Enter a positive number");
        }
    }

    @FXML
    void BackToHomepage(ActionEvent event) {
        try {
            App.setRoot("primary");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void onEvent(Object event) {
        if (event instanceof Dish) {
            dish = (Dish) event;
            DishName.setText(dish.name);
            Price.setText(String.valueOf(dish.price));
        }
    }
    @FXML
    void initialize() {
        System.out.println("Initializing Tertiary Controller");
        EventBus.getDefault().register(this);
    }
}
