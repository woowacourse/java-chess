package application.console;

import application.console.view.ChessView;
import application.console.view.input.InputView;
import application.console.view.output.OutputView;

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
