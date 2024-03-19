import java.util.Scanner;
import model.GameBoard;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        outputView.printStartMessage();
        String command = inputView.readCommand();
        outputView.printGameBoard(gameBoard);
    }
}
