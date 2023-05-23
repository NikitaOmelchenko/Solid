package menu.impl;

import menu.ActionListPage;
import menu.InteractiveChannel;
import view.Presenter;
import model.ShoppingCart;

public class ShoppingCartPage extends ActionListPage {
    protected final ShoppingCart shoppingCart;
    protected final Presenter<ShoppingCart> shoppingCartPresenter;

    public ShoppingCartPage(String pageTitle, ShoppingCart shoppingCart, Presenter<ShoppingCart> shoppingCartPresenter) {
        super(pageTitle);
        this.shoppingCart = shoppingCart;
        this.shoppingCartPresenter = shoppingCartPresenter;
    }

    @Override
    public String getView() {
        return shoppingCartPresenter.toString(shoppingCart) + "\n" + super.getView();
    }

    public void removeItemAction(InteractiveChannel channel) {
        channel.println("Введите ключ записи для удаления:");
        shoppingCart.findById(Long.parseLong(channel.readLine()))
                .ifPresentOrElse(shoppingCart::remove,
                        () -> channel.println("Не найдено"));
    }

}
