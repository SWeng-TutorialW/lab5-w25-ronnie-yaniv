package il.cshaifasweng.OCSFMediatorExample.entities;

public class MenuUpdateEvent {
    public Dish dish;
    public MenuUpdateEvent(Dish dish) {
        this.dish = dish;
    }
}
