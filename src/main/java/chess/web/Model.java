package chess.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dao.TurnDAO;
import chess.domain.ChessBoard;
import chess.domain.Team;

public class Model {
    public static Map<String, Object> empty() {
        return new HashMap<>();
    }

    public static Map<String, Object> turn(ChessBoard chessBoard) {
        Map<String, Object> model = new HashMap<>();
        model.put("turn", chessBoard.getTurn());
        return model;
    }

    public static Map<String, Object> turn() throws SQLException {
        Map<String, Object> model = new HashMap<>();
        model.put("turn", TurnDAO.selectLastTurn().getTurn());
        return model;
    }

    public static Map<String, Object> result(ChessBoard chessBoard) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", "왕이 잡혔습니다.");
        model.put("winner", chessBoard.getWinner());
        model.put("whiteScore", chessBoard.totalScore(Team.WHITE));
        model.put("blackScore", chessBoard.totalScore(Team.BLACK));
        return model;
    }

    public static HashMap<String, Object> result(Double whiteScore, Double blackScore) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("whiteScore", whiteScore);
        model.put("blackScore", blackScore);
        model.put("winner", calculateWinner(whiteScore, blackScore));
        return model;
    }

    private static String calculateWinner(Double whiteScore, Double blackScore) {
        if (whiteScore > blackScore) {
            return "WHITE";
        }
        if (whiteScore < blackScore) {
            return "BLACK";
        }
        return "NONE";
    }
}
