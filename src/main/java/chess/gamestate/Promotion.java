package chess.gamestate;

import chess.domain.ChessBoard;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;

public class Promotion extends Running {

    private final Running beforeRunning;

    protected Promotion(Running beforeRunning) {
        super(beforeRunning);
        this.beforeRunning = beforeRunning;
    }

    @Override
    public GameState run(String command) {
        Piece promotionPiece = PromotionPiece.createPromotionPiece(command, color);
        chessBoard.promotion(promotionPiece, color);
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
