package chess.persistence.dao;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;
import chess.persistence.dao.connector.DataSourceFactory;
import chess.persistence.dto.ChessBoardDTO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDAO {
    public static ChessBoardDAO getInstance() {
        return BoardStatusDAOHandler.INSTANCE;
    }

    public long addBoardStatus(ChessBoardDTO chessBoardDTO) {
        Map<Square, Piece> board = chessBoardDTO.getBoard();
        return board.entrySet().stream()
                .map(index -> {
                    try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
                        String query = "INSERT INTO chess.board(game_id, round_no, square_x, square_y, piece_type, team) VALUES (?,?,?,?,?,?)";
                        PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
                        pstmt.setInt(1, chessBoardDTO.getGameId());
                        pstmt.setInt(2, chessBoardDTO.getRoundNo());
                        pstmt.setInt(3, index.getKey().getX());
                        pstmt.setInt(4, index.getKey().getY());
                        pstmt.setString(5, index.getValue().getType().toString());
                        pstmt.setString(6, index.getValue().getTeam().toString());

                        return pstmt.executeUpdate();
                    } catch (SQLException e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                }).count();
    }

    public ChessBoardDTO findByBoardStatus(ChessBoardDTO chessBoardDTO) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM chess.board WHERE game_id = ? AND round_no = ?";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            pstmt.setInt(1, chessBoardDTO.getGameId());
            pstmt.setInt(2, chessBoardDTO.getRoundNo());

            ResultSet rs = pstmt.executeQuery();

            Map<Square, Piece> board = new HashMap<>();
            while (rs.next()) {
                board.put(Square.of(rs.getInt("square_x"), rs.getInt("square_y"))
                        , Type.valueOf(rs.getString("piece_type")).create(Team.valueOf(rs.getString("team"))));
            }
            chessBoardDTO.setBoard(board);

            return chessBoardDTO;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ChessBoardDTO findByMaxRound(){
        try (Connection connection = DataSourceFactory.getInstance().getConnection()){
            String query = "SELECT * FROM chess.board ORDER BY round_no DESC LIMIT 1";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);

            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()) {
                throw new IllegalArgumentException("Max Round 데이터 없음");
            }

            ChessBoardDTO chessBoardMaxRound = new ChessBoardDTO();
            chessBoardMaxRound.setRoundNo(rs.getInt("round_no"));

            return chessBoardMaxRound;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static class BoardStatusDAOHandler {
        static final ChessBoardDAO INSTANCE = new ChessBoardDAO();
    }
}
