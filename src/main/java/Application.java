import controller.ChessController;
import dao.ChessGameDaoImp;
import service.ChessGameService;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public final class Application {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(
                new InputView(new Scanner(System.in)),
                new OutputView(),
                new ChessGameService(new ChessGameDaoImp())
        );
        chessController.run();
    }
}
