package chess.web;

import chess.controller.ChessManager;

import java.util.HashMap;
import java.util.Map;

public class MoveResponse {

    private final ChessManager chessManager;

    public MoveResponse(ChessManager chessManager) {
        this.chessManager = chessManager;
    }

    public Map<String, Object> getModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("chessPieces", chessManager.getTileDto().getTiles());
        model.put("currentTeam", chessManager.getCurrentTeam());
        model.put("currentTeamScore", chessManager.calculateScore());
        if (chessManager.getWinner().isPresent()) {
            model.put("winner", chessManager.getWinner().get());
        }

        return model;
    }
}
