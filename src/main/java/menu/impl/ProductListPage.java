package menu.impl;

import menu.AbstractItemListPage;
import model.Product;

import java.util.function.Consumer;

public class ProductListPage extends AbstractItemListPage<Product> {
    private static final String ITEM_LINE_FORMAT = "%-80.80s %6d руб";

    public ProductListPage(String pageTitle, Iterable<Product> productSource, Consumer<Product> itemAction) {
        super(
                pageTitle,
                productSource,
                p -> String.valueOf(p.getId()),
                p -> String.format(ITEM_LINE_FORMAT, p.getName(), p.getPrice()),
                itemAction
        );
    }

}
