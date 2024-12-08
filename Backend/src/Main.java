import config.DependencyFactory;
import services.InitialMenu;

public class Main {
    public static void main(String[] args) {
        DependencyFactory factory = new DependencyFactory();
        InitialMenu menu = factory.createInitialMenu();
        menu.handleUserChoice();
    }
}