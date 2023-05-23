package menu;

public interface InteractivePage {
    String getTitle();

    String getView();

    void handle(InteractiveChannel channel);
}
