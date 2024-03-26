import controller.GameController;
import domain.game.ChessGame;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessGame chessGame = ChessGame.initGame();
        GameController gameController = new GameController(new InputView(), new OutputView(), chessGame);
        gameController.run();
    }
}
