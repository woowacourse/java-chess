package chess.controller.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.GameResult;
import chess.domain.board.GameResultBySide;
import chess.domain.piece.Side;

public class GameResultBySideDto {

    private final Map<String, String> gameResultBySideForPrint = new HashMap<>();

    public GameResultBySideDto(GameResultBySide gameResultBySide) {
        Map<Side, GameResult> gotGameResultBySide = gameResultBySide.getGameResultBySide();
        for (Side side : gotGameResultBySide.keySet()) {
            GameResult gameResult = gotGameResultBySide.get(side);
            gameResultBySideForPrint.put(side.getDisplayName(), gameResult.getDisplayName());
        }
    }

    public Map<String, String> getGameResultBySideForPrint() {
        return gameResultBySideForPrint;
    }
}
