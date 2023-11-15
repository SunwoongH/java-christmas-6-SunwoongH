package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private final Map<Menu, Integer> menus;

    public Order() {
        this.menus = new HashMap<>();
    }

    public void addMenu(Menu menu, Integer count) {
        menus.put(menu, count);
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }

    public int countDessertMenu() {
        return menus.keySet()
                .stream()
                .filter(menu -> menu.getKind().equals("dessert"))
                .mapToInt(menus::get)
                .sum();
    }

    public int countMainMenu() {
        return menus.keySet()
                .stream()
                .filter(menu -> menu.getKind().equals("main"))
                .mapToInt(menus::get)
                .sum();
    }

    public int calculateTotalOrderAmount() {
        return menus.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
