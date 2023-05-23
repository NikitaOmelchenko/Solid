package view;

import model.ShoppingCart;
import view.impl.ShoppingCartPresenterImpl;

public interface PresentersRegister {
    <T> Presenter<T> getPresenterFor(Class<T> clazz);

    <T> void registerPresenterFor(Class<ShoppingCart> clazz, ShoppingCartPresenterImpl presenter);

    <T> void registerPresenterFor(Class<T> clazz, Presenter<T> presenter);
}
