package chess.domain.board;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.GamePiece;
import chess.repository.RepositoryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class PositionDAO {

    private final Connection connection;

    public PositionDAO() {
        this.connection = RepositoryUtil.getConnection();
    }

    public int addPosition(int boardId, Position position) throws SQLException {
        String query = "INSERT INTO position (board_id, position_name) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, Integer.toString(boardId));
        pstmt.setString(2, position.getName());
        pstmt.executeUpdate();

        ResultSet generatedKeys = pstmt.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        return 0;
    }

    public List<Position> findPositionsByBoardId(int boardId) throws SQLException {
        String query = "SELECT * FROM position WHERE board_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Integer.toString(boardId));
        ResultSet rs = pstmt.executeQuery();

        List<String> names = new ArrayList<>();
        while (rs.next()) {
            names.add(rs.getString("position_name"));
        }

        return names.stream()
                .map(Position::from)
                .collect(toList());
    }

    public int findIdByPositionName(int boardId, String name) throws SQLException {
        String query = "SELECT * FROM position WHERE board_id = ? AND position_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Integer.toString(boardId));
        pstmt.setString(2, name);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return rs.getInt("id");
    }

    public Map<Position, GamePiece> findBoardContentByBoardId(int boardId) throws SQLException {
        String query = "SELECT position.position_name, piece.piece_name FROM position, piece " +
                "WHERE position.board_id = ? AND position.id = piece.position_id";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Integer.toString(boardId));
        ResultSet rs = pstmt.executeQuery();

        Map<Position, GamePiece> boardContent = new HashMap<>();
        while (rs.next()) {
            String position = rs.getString("position_name");
            String gamePiece = rs.getString("piece_name");
            boardContent.put(Position.from(position), ChessPiece.findGamePieceBy(gamePiece));
        }

        return boardContent;
    }
}
