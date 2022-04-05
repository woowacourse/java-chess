package chess.domain.turn;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Map.Entry;

public class RunningTurn implements GameTurn {

    private final ChessBoard chessBoard;
    private final Color color;

    public RunningTurn(ChessBoard chessBoard, Color color) {
        this.chessBoard = chessBoard;
        this.color = color;
    }

    @Override
    public Turn nextTurn() {
        if (chessBoard.isFinished()) {
            return Turn.END;
        }
        if (chessBoard.isPromotionStatus(color)) {
            return color.currentTurn();
        }
        return color.reverseTurn();
    }

    @Override
    public Turn currentTurn() {
        return color.currentTurn();
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
    public boolean isEnd() {
        return false;
    }

    @Override
    public Map<Color, Double> currentScore() {
        return chessBoard.calcualteScoreStatus();
    }

    @Override
    public Map<Position, Piece> pieces() {
        return chessBoard.getPieces();
    }
}
