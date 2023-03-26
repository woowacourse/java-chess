package chess;

import chess.controller.ChessController;
import chess.controller.PlayerController;
import chess.domain.player.Player;

public class ChessApplication {

    public static void main(String[] args) {
        PlayerController playerController = new PlayerController();
        ChessController controller = new ChessController();

        Player player = playerController.handle();
        controller.run();
    }
}
