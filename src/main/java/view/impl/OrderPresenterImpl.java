package view.impl;

import view.Presenter;
import model.Delivery;
import model.Order;
import model.ShoppingCart;

import java.util.Objects;
import java.util.Optional;

public class OrderPresenterImpl implements Presenter<Order> {
    protected final Presenter<ShoppingCart> shoppingCartPresenter;
    protected final Presenter<Delivery> deliveryPresenter;

    public OrderPresenterImpl(ShoppingCartPresenterImpl shoppingCartPresenter, DeliveryPresenterImpl deliveryPresenter) {
        this.shoppingCartPresenter = shoppingCartPresenter;
        this.deliveryPresenter = deliveryPresenter;
    }

    @Override
    public String toString(Optional<Order> optOrder) {
        StringBuilder sb = new StringBuilder();
        optOrder.ifPresent(order ->
            sb.append("Заказ ").append(Objects.toString(order.getId(), "Новый")).append(" (").append(order.getOrderStatus()).append(")").append("\n")
                    .append(order.getDateTime()).append("\n")
                    .append("\n")
                    .append(shoppingCartPresenter.toString(order.getShoppingCart())).append("\n")
                    .append("\n")
                    .append(deliveryPresenter.toString(order.getDelivery())).append("\n")
                    .append("\n")
                    .append("Итог: ").append(order.getTotal()).append(" руб").append("\n")
        );
        return sb.toString();
    }
}
