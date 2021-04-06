package chess.domain.board;

import chess.dao.BackupBoardDao;
import chess.dao.InitialBoardDao;
import chess.dao.RoomDao;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.HashMap;

public class InitializedBoardFromDb {

    public InitializedBoardFromDb() {
    }

    public HashMap<Position, Piece> initBoard() {
        InitialBoardDao initialBoardDao = new InitialBoardDao();
        return initialBoardDao.findInitialBoard();
    }

    public HashMap<Position, Piece> continueBoard(String roomName) {
        BackupBoardDao backupBoardDao = new BackupBoardDao();
        return backupBoardDao.findPlayingBoardByRoom(roomName);
    }

    public PieceColor continueTurn(String roomName) {
        RoomDao roomDao = new RoomDao();
        return PieceColor.pieceColorByName(roomDao.findRoomTurnColor(roomName));
    }
}
