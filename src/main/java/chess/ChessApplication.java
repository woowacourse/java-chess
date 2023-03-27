package chess;

import chess.controller.ChessGameController;
import chess.controller.ChessRoomController;
import chess.controller.PlayerController;
import chess.dto.ChessRoomDto;
import chess.dto.PlayerDto;

public class ChessApplication {

    public static void main(String[] args) {
        PlayerController playerController = new PlayerController();
        ChessRoomController chessRoomController = new ChessRoomController();
        ChessGameController chessGameController = new ChessGameController();

        PlayerDto playerDto = playerController.handle();
        ChessRoomDto chessRoomDto = chessRoomController.handle(playerDto);
        chessGameController.run(chessRoomDto);
    }
}
