package chess.application.chessround;

import chess.application.ChessJDBCTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(isWhiteTurn);

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        chessJDBCTemplate.executeUpdate(query, queryValues);
    }

    public boolean getPlayerTurn() {
        String query = "SELECT is_white_turn FROM chess_turn WHERE round=1";

        ChessJDBCTemplate chessJDBCTemplate = ChessJDBCTemplate.getInstance();
        List<Map<String, Object>> results = chessJDBCTemplate.executeQuery(query);
        Map<String, Object> resultRow = results.get(0);
        return (boolean) resultRow.get("is_white_turn");
    }
}
