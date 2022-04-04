package chess.domain.turn;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import java.util.Map.Entry;

public class RunningTurn implements GameTurn {

    private final ChessBoard chessBoard;
    private final Color color;

    public RunningTurn(ChessBoard chessBoard, Color color) {
        this.chessBoard = chessBoard;
        this.color = color;
    }

    @Override
    public GameTurn nextTurn() {
        if (chessBoard.isPromotionStatus(color)) {
            return this;
        }
        if (chessBoard.isFinished()) {
            return new EndTurn(chessBoard);
        }
        return new RunningTurn(chessBoard, color.reverseColor());
    }

    @Override
    public void movePiece(Position source, Position target) {
        chessBoard.movePiece(source, target, color);
    }

    @Override
    public Entry<Position, Piece> promotion(PromotionPiece promotionPiece) {
        return chessBoard.promotion(promotionPiece, color);
    }

    @Override
    public Color color() {
        return color;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
