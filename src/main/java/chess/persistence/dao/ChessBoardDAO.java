package chess.persistence.dao;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.domain.piece.core.Type;
import chess.persistence.dao.core.JDBCTemplate;
import chess.persistence.dto.ChessBoardDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardDAO {
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();
    private static final int FAILED = -1;

    private ChessBoardDAO() {
    }

    public static ChessBoardDAO getInstance() {
        return BoardStatusDAOHandler.INSTANCE;
    }

    public long addBoardStatus(ChessBoardDTO chessBoardDTO) {
        try {
            return chessBoardDTO.getBoard().entrySet().stream()
                    .map(entry -> {
                        String query = "INSERT INTO chess.board(game_id, round_no, square_x, square_y, piece_type, team) VALUES (?,?,?,?,?,?)";
                        List<Object> params = new ArrayList<>();
                        params.add(chessBoardDTO.getGameId());
                        params.add(chessBoardDTO.getRoundNo());
                        params.add(entry.getKey().getX());
                        params.add(entry.getKey().getY());
                        params.add(entry.getValue().getType().toString());
                        params.add(entry.getValue().getTeam().toString());

                        return JDBC_TEMPLATE.excuteUpdate(query, params);
                    }).count();
        } catch (IllegalArgumentException e) {
            return FAILED;
        }
    }

    public ChessBoardDTO findByBoardStatus(ChessBoardDTO chessBoardDTO) {
        String query = "SELECT * FROM chess.board WHERE game_id = ? AND round_no = ?";

        List<Object> params = new ArrayList<>();
        params.add(chessBoardDTO.getGameId());
        params.add(chessBoardDTO.getRoundNo());

        chessBoardDTO.setBoard(JDBC_TEMPLATE.queryForObject(query, params, rs -> {
                    Map<Square, Piece> board = new HashMap<>();
                    while (rs.next()) {
                        board.put(Square.of(rs.getInt("square_x"), rs.getInt("square_y"))
                                , Type.valueOf(rs.getString("piece_type")).create(Team.valueOf(rs.getString("team"))));
                    }
                    return board;
                }
        ));

        return chessBoardDTO;

    }

    public int findMaxRoundByGameId(int gameId) {
        String query = "SELECT * FROM chess.board WHERE game_id=? ORDER BY round_no DESC LIMIT 1";
        List<Object> params = new ArrayList<>();
        params.add(gameId);
        try {
            return JDBC_TEMPLATE.queryForObject(query, params, rs -> {
                int result = rs.next() ? rs.getInt("round_no") : -1;
                rs.close();
                return result;
            });
        } catch (IllegalArgumentException e) {
            return FAILED;
        }

    }

    private static class BoardStatusDAOHandler {
        static final ChessBoardDAO INSTANCE = new ChessBoardDAO();
    }
}
