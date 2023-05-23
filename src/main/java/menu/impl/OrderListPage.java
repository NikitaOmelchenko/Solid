package menu.impl;

import menu.AbstractItemListPage;
import model.Order;

import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class OrderListPage extends AbstractItemListPage<Order> {
    private static String ITEM_LINE_FORMAT = "Заказ %d от %s %d строк на сумму %d руб. (%s)";
    protected final Iterable<Order> ordersSource;

    public OrderListPage(String pageTitle, Iterable<Order> orderSource, Consumer<Order> itemAction) {
        super(
                pageTitle,
                orderSource,
                o -> String.valueOf(o.getId()),
                o -> String.format(ITEM_LINE_FORMAT,
                        o.getId(),
                        o.getDateTime().format(DateTimeFormatter.ISO_DATE),
                        o.getShoppingCart().size(),
                        o.getTotal(),
                        o.getOrderStatus()
                ),
                itemAction
        );
        this.ordersSource = orderSource;
    }

    @Override
    public String getView() {
        updateView();
        return getMapView(itemActionsMap) + "\n" +
                getMapView(actions);
    }

}
