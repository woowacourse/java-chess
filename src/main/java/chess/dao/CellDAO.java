package chess.dao;

import static chess.util.RepositoryUtil.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.GamePiece;
import chess.domain.piece.GamePieces;

public class CellDAO {

    public void addCells(Board board, int board_id) throws SQLException {
        String query = "INSERT INTO cell (board_id, position, piece) VALUES ";
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Position, GamePiece> entry : board.getBoard().entrySet()) {
            str.append("(");
            str.append(board_id);
            str.append(", '");
            str.append(entry.getKey().getName());
            str.append("', '");
            str.append(entry.getValue().getName());
            str.append("'),\n");
        }

        int index = str.lastIndexOf(",\n");
        str.replace(index, index + 1, ";");
        query = query + str.toString();

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public Map<Position, GamePiece> findByBoardId(int board_id) throws SQLException {
        String query = "SELECT * FROM cell WHERE board_id = ?";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, board_id);
        ResultSet rs = pstmt.executeQuery();

        Map<Position, GamePiece> board = new HashMap<>();

        while(rs.next()) {
            Position position = Position.from(rs.getString("position"));
            GamePiece gamePiece = GamePieces.from(rs.getString("piece"));
            board.put(position, gamePiece);
        }

        return board;
    }
}
