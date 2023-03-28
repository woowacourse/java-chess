import controller.MainController;
import dao.DataBaseChessGameDao;

public class Application {

    public static void main(String[] args) {
        MainController mainController = new MainController(new DataBaseChessGameDao());
        mainController.run();
    }
}
