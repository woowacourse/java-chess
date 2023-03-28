package chess;

import chess.controller.ControllerFactory;
import chess.controller.main.MainController;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChessGameApplication {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            final MainController controller = ControllerFactory.mainController();
            controller.run();
        });
        executor.shutdown();
    }
}
