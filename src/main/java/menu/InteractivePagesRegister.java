package menu;

public interface InteractivePagesRegister {
    InteractivePage getPage(String name);

    void registerPage(String nameId, InteractivePage page);
}
