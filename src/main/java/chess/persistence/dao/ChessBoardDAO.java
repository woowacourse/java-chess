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

    private ChessBoardDAO() {
    }

    public void addBoardStatus(ChessBoardDTO chessBoardDTO) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "INSERT INTO chess.board(game_id, round_no, square_x, square_y, piece_type, team) VALUES (?,?,?,?,?,?)";
            for (Square square : chessBoardDTO.getBoard().keySet()) {
                PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
                pstmt.setInt(1, chessBoardDTO.getGameId());
                pstmt.setInt(2, chessBoardDTO.getRoundNo());
                pstmt.setInt(3, square.getX());
                pstmt.setInt(4, square.getY());
                pstmt.setString(5, chessBoardDTO.getBoard().get(square).getType().toString());
                pstmt.setString(6, chessBoardDTO.getBoard().get(square).getTeam().toString());

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> Board 데이터 넣는 부분");
        }
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
            throw new IllegalArgumentException("SQLException 발생 -> 보드상태 찾는 부분");
        }
    }

    public int findMaxRoundByGameId(int gameId) {
        try (Connection connection = DataSourceFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM chess.board WHERE game_id=? ORDER BY round_no DESC LIMIT 1";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            pstmt.setInt(1, gameId);

            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                return -1;
            }

            return rs.getInt("round_no");
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQLException 발생 -> maxRound 찾는 부분");
        }
    }

    private static class BoardStatusDAOHandler {
        static final ChessBoardDAO INSTANCE = new ChessBoardDAO();
    }
}
