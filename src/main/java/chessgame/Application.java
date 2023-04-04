package chessgame;

import chessgame.controller.Controller;
import chessgame.service.ChessGameService;
import chessgame.view.InputView;
import chessgame.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        ChessGameService chessGameService = new ChessGameService();
        Controller controller = new Controller(inputView, outputView, chessGameService);
        controller.run();
    }
}
