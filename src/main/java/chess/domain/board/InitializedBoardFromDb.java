package chess.domain.board;

import chess.dao.BackupBoardDao;
import chess.dao.InitialBoardDao;
import chess.dao.RoomDao;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.HashMap;
import java.util.Map;

public class InitializedBoardFromDb {

    public InitializedBoardFromDb() {
    }

    public HashMap<Position, Piece> initBoard() {
        HashMap<Position, Piece> board = new HashMap<>();
        InitialBoardDao initialBoardDao = new InitialBoardDao();

        Position.cachedPosition()
            .keySet()
            .forEach(position -> board.put(Position.from(position),
                initialBoardDao.findInitialBoardPieceAtPosition(position)));

        return board;
    }

    public Map<Position, Piece> continueBoard(String roomName) {
        HashMap<Position, Piece> board = new HashMap<>();
        BackupBoardDao backupBoardDao = new BackupBoardDao();

        Position.cachedPosition()
            .keySet()
            .forEach(position -> board.put(Position.from(position),
                backupBoardDao.findPlayingBoardByRoom(roomName, position)));
        return board;
    }

    public PieceColor continueTurn(String roomName) {
        RoomDao boardDao = new RoomDao();
        return PieceColor.pieceColorByName(boardDao.findRoomTurnColor(roomName));
    }
}
