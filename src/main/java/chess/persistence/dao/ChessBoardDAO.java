package chess.persistence.dao;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import chess.persistence.dao.core.JDBCTemplate;
import chess.persistence.dto.ChessBoardDTO;

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
                        System.err.println(chessBoardDTO.getRoundNo() + "이거다");
                        return JDBC_TEMPLATE.update(query, chessBoardDTO.getGameId()
                                , chessBoardDTO.getRoundNo(), entry.getKey().getX()
                                , entry.getKey().getY(), entry.getValue().getType().toString()
                                , entry.getValue().getTeam().toString());
                    }).count();
        } catch (IllegalArgumentException e){
            return FAILED;
        }
    }

    public ChessBoardDTO findByBoardStatus(ChessBoardDTO chessBoardDTO) {
        String query = "SELECT * FROM chess.board WHERE game_id = ? AND round_no = ?";
        Map<Square, Piece> getBoard = JDBC_TEMPLATE.queryForBoard(query, chessBoardDTO.getGameId(), chessBoardDTO.getRoundNo());
        chessBoardDTO.setBoard(getBoard);

        return chessBoardDTO;

    }

    public int findMaxRoundByGameId(int gameId) {
        String query = "SELECT * FROM chess.board WHERE game_id=? ORDER BY round_no DESC LIMIT 1";
        try {
            return JDBC_TEMPLATE.queryForBoard(query, gameId);
        } catch (IllegalArgumentException e){
            return FAILED;
        }

    }

    private static class BoardStatusDAOHandler {
        static final ChessBoardDAO INSTANCE = new ChessBoardDAO();
    }
}
