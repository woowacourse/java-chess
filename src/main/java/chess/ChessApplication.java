package chess;

import chess.controller.ChessController;
import chess.controller.ChessRoomController;
import chess.controller.PlayerController;
import chess.domain.player.Player;
import chess.domain.room.ChessRoom;

public class ChessApplication {

    public static void main(String[] args) {
        PlayerController playerController = new PlayerController();
        Player player = playerController.handle();

        ChessRoomController chessRoomController = new ChessRoomController();
        ChessRoom chessRoom = chessRoomController.handleChessRoom(player);

        ChessController controller = new ChessController(chessRoom);
        controller.run();
    }
}
