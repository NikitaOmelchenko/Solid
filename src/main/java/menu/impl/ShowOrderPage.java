package menu.impl;

import menu.ActionListPage;
import view.Presenter;
import model.Order;

public class ShowOrderPage extends ActionListPage {
    protected final Order order;
    protected final Presenter<Order> orderPresenter;

    public ShowOrderPage(Order order, Presenter<Order> orderPresenter) {
        super("Просмотр заказа");
        this.order = order;
        this.orderPresenter = orderPresenter;
    }

    @Override
    public String getView() {
        return orderPresenter.toString(order) + "\n" + super.getView();
    }
}
