import chess.ChessMachine;
import chess.InputView;
import chess.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessMachine chessMachine = new ChessMachine(new OutputView(), new InputView());
        chessMachine.run();
    }
}
