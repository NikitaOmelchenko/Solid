package menu;

public abstract class AbstractPage implements InteractivePage {
    protected final String pageTitle;

    public AbstractPage(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    @Override
    public String getTitle() {
        return pageTitle;
    }
}
