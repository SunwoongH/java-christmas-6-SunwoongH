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

    public long countDessertMenu() {
        return menus.keySet()
                .stream()
                .filter(menu -> menu.getKind().equals("dessert"))
                .count();
    }

    public long countMainMenu() {
        return menus.keySet()
                .stream()
                .filter(menu -> menu.getKind().equals("main"))
                .count();
    }
}
