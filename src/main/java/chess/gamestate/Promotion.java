package chess.gamestate;

import chess.domain.ChessBoard;
import chess.domain.PromotionPiece;

public class Promotion extends Running {

    private final Running beforeRunning;

    public Promotion(Running beforeRunning) {
        super(beforeRunning);
        this.beforeRunning = beforeRunning;
    }

    @Override
    public GameState run(String command) {
        chessBoard.promotion(PromotionPiece.createPromotionPiece(command), color);
        return beforeRunning.otherState(chessBoard);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    protected Running otherState(ChessBoard chessBoard) {
        throw new IllegalStateException("Promotion은 상대 턴을 확인할 수 없습니다.");
    }
}
