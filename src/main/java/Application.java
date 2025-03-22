import chess.ChessRunner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChessRunner chessRunner = new ChessRunner(new InputView(),new OutputView());
        chessRunner.run();
    }
}
