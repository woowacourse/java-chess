package chess.domain.game.command;

import chess.domain.game.state.GameState;
import chess.domain.game.state.PromotionState;
import chess.domain.game.state.RunningState;

public class PromotionCommand implements Command {

    private final PromotionState promotionState;
    private final String pieceName;

    private PromotionCommand(final PromotionState promotionState, final String pieceName) {
        this.promotionState = promotionState;
        this.pieceName = pieceName;
    }

    public static PromotionCommand of(final RunningState runningState, final String pieceName) {
        final PromotionState promotionState = convertToPromotionState(runningState);
        return new PromotionCommand(promotionState, pieceName);
    }

    private static PromotionState convertToPromotionState(final RunningState runningState) {
        validatePromotionState(runningState);
        return (PromotionState) runningState;
    }

    private static void validatePromotionState(final RunningState runningState) {
        if (!runningState.isPromotable()) {
            throw new IllegalStateException("프로모션이 가능한 게임 상태가 아닙니다.");
        }
    }

    @Override
    public GameState execute() {
        return promotionState.run(pieceName);
    }
}
