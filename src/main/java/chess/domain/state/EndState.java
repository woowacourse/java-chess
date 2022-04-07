package chess.domain.state;

import static chess.domain.state.Turn.END;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Map.Entry;

public class EndState implements ChessGameState {

    private final ChessBoard chessBoard;

    public EndState(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public Turn nextTurn() {
        throw new IllegalStateException("종료된 게임은 다음 턴이 없습니다.");
    }

    @Override
    public Turn currentTurn() {
        return END;
    }

    @Override
    public void movePiece(Position source, Position target) {
        throw new IllegalStateException("종료된 게임은 기물을 움직일 수 없습니다.");
    }

    @Override
    public Entry<Position, Piece> promotion(PromotionPiece promotionPiece) {
        throw new IllegalStateException("종료된 게임은 promotion할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public Map<Color, Double> currentScore() {
        throw new IllegalStateException("종료된 게임은 score를 계산할 수 없습니다.");
    }

    @Override
    public Map<Position, Piece> pieces() {
        return chessBoard.getPieces();
    }
}
