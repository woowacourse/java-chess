package chess.web;

import chess.controller.ChessManager;
import chess.database.ChessDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StartResponse {
    private final ChessManager chessManager;
    private final ChessDao chessCommandDao;

    public StartResponse(ChessManager chessManager, ChessDao chessDao) {
        this.chessManager = chessManager;
        this.chessCommandDao = chessDao;
    }

    public Map<String, Object> getModel() throws SQLException {
        Map<String, Object> model = new HashMap<>();
        model.put("chessPieces", chessManager.getTileDto().getTiles());
        model.put("currentTeam", chessManager.getCurrentTeam());
        model.put("currentTeamScore", chessManager.calculateScore());
        if (!chessCommandDao.selectCommands().isEmpty()) {
            model.put("haveLastGameRecord", "true");
        }

        return model;
    }
}
