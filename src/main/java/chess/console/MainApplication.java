package chess.console;

import chess.console.view.ChessView;
import chess.console.view.input.InputView;
import chess.console.view.output.OutputView;

public class MainApplication {

    public static void main(final String[] args) {
        final MainApplication application = new MainApplication();
        application.run();
    }

    private void run() {
        final ChessView chessView = new ChessView(new InputView(), new OutputView());
        final ChessApplication application = new ChessApplication(chessView);
        application.run();
    }
}
