package menu;

public interface InteractiveChannel {
    String readLine();

    void write(Object obj);

    default void println(Object obj) {
        write(String.valueOf(obj));
        write("\n");
    }

}
