package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Side;

public class GameResultBySide {

    private final Map<Side, GameResult> gameResultBySide = new HashMap<>();

    public GameResultBySide() {
        initGameResultBySide();
    }

    private void initGameResultBySide() {
        gameResultBySide.put(Side.WHITE, GameResult.DRAW);
        gameResultBySide.put(Side.BLACK, GameResult.DRAW);
    }

    public void updateGameResultBySide(Side side, GameResult gameResultToUpdate) {
        gameResultBySide.replace(side, gameResultToUpdate);
    }

    public Map<Side, GameResult> getGameResultBySide() {
        return Map.copyOf(gameResultBySide);
    }
}
