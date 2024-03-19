import model.GameBoard;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();
        OutputView outputView = new OutputView();
        outputView.printGameBoard(gameBoard);
    }
}
