package chess;

import chess.controller.ChessGameController;
import chess.controller.ChessRoomController;
import chess.controller.PlayerController;
import chess.domain.player.Player;
import chess.domain.room.ChessRoom;

public class ChessApplication {

    public static void main(String[] args) {
        PlayerController playerController = new PlayerController();
        ChessRoomController chessRoomController = new ChessRoomController();
        ChessGameController chessGameController = new ChessGameController();

        Player player = playerController.handle();
        ChessRoom chessRoom = chessRoomController.handle(player);
        chessGameController.run(chessRoom);
    }
}
