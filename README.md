﻿## Консольное приложение "Магазин"

Возможности программы:

- Вывод доступных для покупки товаров
- Составление продуктовой корзины пользователя
- Оформление заказа с учетом доставки
- Трекинг заказа в системе доставки

### Примеры избегания магических чисел

1. Класс `service.DeliveryServiceImpl` использует константу [MAX_VALUE](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/service/DeliveryServiceImpl.java) для ограничения максимального значения генерируемой стоимости доставки.

2. Класс `model.ShoppingCart` использует константу [INITIAL_COUNT_VALUE](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/model/ShoppingCart.java) как первоначальное значение счетчика определенного товара в корзине.

### Примеры использования принципа DRY (Don’t Repeat Yourself)

1. Пакет классов для создания интерактивных страниц консольного меню `menu` построен через дополнение уже существующих классов наследованием или передачей функций в параметрах.
  - Класс `menu.impl.ActionListPage` умеет выводить на экран список возможных команд с идентификаторами и выполнять их. Класс `menu.impl.ShoppingCartPage` расширяет класс `ActionListPage` и с минимальными изменениями [добавляет](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/menu/impl/ShoppingCartPage.java) возможность выводить список товаров в корзине. Нам не приходится снова реализовывать вывод меню заданных команд.
2. При выводе на экран текстового представления `model.Order` используется функционал класса `view.impl.OrderPresenterImpl`. Класс сам использует другие преобразователи, получаемые из параметров: `ShoppingCartPresenterImpl` и `DeliveryPresenterImpl`. Так мы [избегаем повторения](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/view/impl/OrderPresenterImpl.java) реализации функций преобразования.

### Примеры применения принципов SOLID

#### Single Responsibility Principle

1. Все классы пакета `model` предназначены только для хранения и передачи данных. Они ничего не выводят на экран. Не вызывают системные методы. Не читают ввод. Например класс [Delivery](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/model/Delivery.java).

2. Классы пакета `view.impl` предназначены только для создания текстового представления соответствующих POJO моделей (`Order`, `Delivery`, `ShoppingCart`). Они не обрабатывают пользовательский ввод. Ничего не выводят на экран. Не изменяют переданные им объекты. Например класс [DeliveryPresenterImpl](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/view/impl/DeliveryPresenterImpl.java).

#### Open Closed Principle

1. Класс `menu.ActionListPage` выводит обрабатываемые команды в виде меню. Его наследник `.menu.ShowOrderPage` переопределяет метод [.getView()](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/menu/impl/ShowOrderPage.java) и выводит текстовое представление `Order` до пунктов меню с командой `Выход`.

2. Класс `ConsoleShopApplication` спроектирован так, чтобы предоставить его наследникам возможность расширения функционала без изменения исходного класса. Например переопределение метода [.configureMenu()](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/ConsoleShopApplication.java)  позволит создать свой набор интерактивных экранов. А переопределение метода [.configurePresenters()](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/ConsoleShopApplication.java) позволит дополнить набор преобразователей для новых объектов.

#### Liskov Substitution Principle

1. Есть цепочка наследования классов и интерфейсов:
```
 InteractivePage      - базовый интерфейс
        |
   AbstractPage       - хранит базовые данные
        |
  ActionListPage      - выводит список команд и реагирует на них
        |
AbstractItemListPage  - выводит список элементов и команд. Реагирует на команды.
        |
  ProductListPage     - выводит список товаров из источника + весь функционал выше
```
Класс `menu.InteractivePagesHandler` занимается сменой страниц, передачей им пользовательского ввода и передачи результата. Ему для работы достаточно знать, что они потомки `InteractivePage`. Так выполняется принцип, когда [потомки могут замещать предка](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/menu/InteractivePagesHandler.java).

#### Interface Segregation Principle

Класс `ConsoleShopApplication` реализует интерфейсы [PresentersRegister](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/view/PresentersRegister.java) и [InteractivePagesRegister](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/menu/InteractivePagesRegister.java). Они спроектированы минималистично на случай если функцию регистра объектов будет выполнять внешний класс. В таком случае при реализации одного регистра, не придется имплементировать методы относящиеся ко второму.

#### Dependency Inversion Principle

Объекту класса `view.impl.OrderPresenterImpl` в работе требуется представить в текстовом виде объекты `ShoppingCart` и `Delivery`.

Он не создает экземпляры `ShoppingCartPresenterImpl` и `DeliveryPresenterImpl` самостоятельно, чтобы не зависеть от конкретных классов и реализации. `DeliveryPresenterImpl` получает необходимые ему объекты через параметры конструктора.

Готовые объекты внедряются через [использование интерфейса](https://github.com/NikitaOmelchenko/Solid/blob/main/src/main/java/view/impl/OrderPresenterImpl.java) `Presenter<T>`. Поэтому внедрить можно любую реализацию подходящую по типу.