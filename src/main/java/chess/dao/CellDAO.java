package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.GamePiece;
import chess.domain.piece.GamePieces;
import chess.util.DBConnector;

public class CellDAO {

    private DBConnector dbConnector;

    public CellDAO(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void addCells(Board board, int board_id) throws SQLException {
        for (Map.Entry<Position, GamePiece> entry : board.getBoard().entrySet()) {
            dbConnector.executeUpdate("INSERT INTO cell (board_id, position, piece) VALUES (?, ?, ?)",
                    String.valueOf(board_id), entry.getKey().getName(), entry.getValue().getName());
        }
    }

    public Map<Position, GamePiece> findCellsByBoardId(int board_id) throws SQLException {
        ResultSet rs = dbConnector.executeQuery("SELECT * FROM cell WHERE board_id = ?", String.valueOf(board_id));
        Map<Position, GamePiece> board = new HashMap<>();

        while (rs.next()) {
            Position position = Position.from(rs.getString("position"));
            GamePiece gamePiece = GamePieces.from(rs.getString("piece"));
            board.put(position, gamePiece);
        }

        return board;
    }

    public Map<Position, GamePiece> updateCellsByBoardId(Board board, int board_id) throws SQLException {
        for (Map.Entry<Position, GamePiece> entry : board.getBoard().entrySet()) {
            dbConnector.executeUpdate("UPDATE cell SET piece = ? WHERE position = ? AND board_id = ?",
                    entry.getValue().getName(), entry.getKey().getName(), String.valueOf(board_id));
        }

        return findCellsByBoardId(board_id);
    }

    public boolean deleteCellsByUser(int board_id) throws SQLException {
        dbConnector.executeUpdate("DELETE FROM cell WHERE board_id = ?", String.valueOf(board_id));

        return findCellsByBoardId(board_id) == null;
    }
}
