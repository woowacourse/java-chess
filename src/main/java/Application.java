import controller.InputController;
import java.util.Scanner;
import model.Command;
import model.GameBoard;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        InputController inputController = new InputController(inputView, outputView);
        outputView.printStartMessage();
        while (inputController.getCommand() != Command.END) {
            outputView.printGameBoard(gameBoard);
        }
    }
}
