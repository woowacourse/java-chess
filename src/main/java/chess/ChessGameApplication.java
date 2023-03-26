package chess;

import chess.controller.Controller;
import chess.controller.ControllerFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChessGameApplication {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            final Controller controller = ControllerFactory.mainController();
            controller.run();
        });
        executor.shutdown();
    }
}
