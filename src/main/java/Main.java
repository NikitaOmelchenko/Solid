

import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Product> productList = List.of(
                new Product(1L, "Корзина металлическая для хранения овощей и фруктов, 26,5*24,5*17 см MARMITON", 700L),
                new Product(2L, "Одеяло Соната Овечья шерсть, всесезонное, 140 х 205 см, бежевый", 835L),
                new Product(3L, "Столовый сервиз Luminarc Diwali H5869/P2920/P2947/P2961, 6 персон, 19 предм.", 2900L),
                new Product(4L, "Скатерть CobraJet гибкое стекло, 70х50 см, толщина 1 мм, прозрачный", 890L),
                new Product(5L, "Чайник электрический SCARLETT SC-EK27G93 с подсветкой, объем 1.7 л", 1479L)
        );

        List<Order> ordersList = new ArrayList<>();

        ConsoleShopApplication app = new ConsoleShopApplication(productList, ordersList);
        app.start();

    }

}
