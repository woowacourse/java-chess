import controller.MainController;
import database.db.DatabaseDao;

public class Application {

    public static void main(String[] args) {
        MainController mainController = new MainController(new DatabaseDao());
        mainController.run();
    }
}
