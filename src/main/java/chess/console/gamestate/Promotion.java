package chess.console.gamestate;

import chess.domain.ChessBoard;
import chess.domain.PromotionPiece;

public final class Promotion extends Running {

    private final Running beforeRunning;

    public Promotion(Running beforeRunning) {
        super(beforeRunning);
        this.beforeRunning = beforeRunning;
    }

    @Override
    public GameState run(String requestCommand) {
        chessBoard.promotion(PromotionPiece.createPromotionPiece(requestCommand), color);
        return beforeRunning.otherState(chessBoard);
    }

    @Override
    public Running otherState(ChessBoard chessBoard) {
        throw new IllegalStateException("Promotion은 상대 턴을 확인할 수 없습니다.");
    }
}
