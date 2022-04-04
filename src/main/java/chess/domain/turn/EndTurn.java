package chess.domain.turn;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import java.util.Map.Entry;

public class EndTurn implements GameTurn {

    private final ChessBoard chessBoard;

    public EndTurn(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameTurn nextTurn() {
        throw new IllegalStateException("종료된 게임은 다음 턴이 없습니다.");
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
    public Color color() {
        return chessBoard.winner();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
