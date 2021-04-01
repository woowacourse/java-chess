package chess.domain.board;

import chess.dao.BoardDao;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InitializedBoardFromDb {

    public InitializedBoardFromDb() {
    }

    public HashMap<Position, Piece> initBoard() {
        HashMap<Position, Piece> board = new HashMap<>();
        BoardDao boardDao = new BoardDao();

        Position.cachedPosition()
            .keySet()
            .stream()
            .forEach(position -> {
                try {
                    board.put(Position.from(position), boardDao.findInitialBoardPieceAtPosition(position));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

        return board;
    }

    public Map<Position, Piece> continueBoard(String roomName) {
        HashMap<Position, Piece> board = new HashMap<>();
        BoardDao boardDao = new BoardDao();

        Position.cachedPosition()
            .keySet()
            .stream()
            .forEach(position -> {
                try {
                    board.put(Position.from(position), boardDao.findPlayingBoardByRoom(roomName, position));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

        return board;
    }

    public PieceColor continueTurn(String roomName) throws SQLException {
        BoardDao boardDao = new BoardDao();
        return PieceColor.pieceColorByName(boardDao.findRoomTurnColor(roomName));
    }
}
