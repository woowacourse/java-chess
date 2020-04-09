package chess.web;

import chess.controller.ChessManager;
import chess.database.ChessCommandDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StartResponse {
    private final ChessManager chessManager;
    private ChessCommandDao chessCommandDao;

    public StartResponse(ChessManager chessManager, ChessCommandDao chessCommandDao) {
        this.chessManager = chessManager;
        this.chessCommandDao = chessCommandDao;
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
