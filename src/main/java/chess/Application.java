package chess;

import chess.controller.Controller;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();

        Controller controller = Controller.create(inputView, outputView);
        controller.run();

        scanner.close();
    }
}
