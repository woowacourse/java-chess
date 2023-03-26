package chess.controller;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.dao.ChessRoomDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.player.Player;
import chess.domain.room.ChessRoom;

public class ChessRoomController {

    public ChessRoom handle(final Player player) {
        ChessRoom chessRoom = ChessRoomDao.findByPlayer(player);

        if (chessRoom == null) {
            Board board = BoardDao.create();
            ChessGame chessGame = ChessGameDao.create(board);
            chessRoom = ChessRoomDao.create(chessGame, player);
        }

        return chessRoom;
    }
}
