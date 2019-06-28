package chess.application.chessround;

import chess.application.ChessJDBCTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ChessTurnDAO {
    private static ChessTurnDAO chessTurnDAO = null;

    private ChessTurnDAO() {
    }

    public static ChessTurnDAO getInstance() {
        if (chessTurnDAO == null) {
            chessTurnDAO = new ChessTurnDAO();
        }
        return chessTurnDAO;
    }

    public void updatePlayerTurn(boolean isWhiteTurn) {
        String query = "UPDATE chess_turn SET is_white_turn=? WHERE round=1";

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        chessJDBCTemplate.executeUpdate(query, (PreparedStatement pstmt) -> {
            pstmt.setBoolean(1, isWhiteTurn);

            return null;
        });
    }

    public boolean getPlayerTurn() {
        String query = "SELECT is_white_turn FROM chess_turn WHERE round=1";

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        List<Boolean> results = chessJDBCTemplate.executeQuery(query,
                (ResultSet rs, int rowNum) -> rs.getBoolean("is_white_turn")
        );

        return results.get(0);
    }
}
