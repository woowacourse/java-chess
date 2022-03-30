package chess;

import chess.controller.WebController;
import chess.repository.MemoryGameRepository;

public class WebApplication {
    public static void main(String[] args) {
        WebController controller = new WebController(new MemoryGameRepository());
        controller.run();
    }
}
