package chess.controller;

import chess.dao.ChessRoomDao;
import chess.domain.player.Player;
import chess.domain.room.ChessRoom;

public class ChessRoomController {

    public ChessRoom handle(final Player player) {
        ChessRoom chessRoom = ChessRoomDao.findByPlayer(player);

        return chessRoom;
    }
}
