package chess.domain.piece;

import chess.domain.piece.common.Bishop;
import chess.domain.piece.common.Queen;
import chess.domain.piece.common.Rook;
import chess.domain.piece.ranged.King;
import chess.domain.piece.ranged.Knight;
import java.util.function.Function;

public enum PieceType {
    QUEEN("q", 9, Queen::new),
    ROOK("r", 5, Rook::new),
    BISHOP("b", 3, Bishop::new),
    KNIGHT("n", 2.5, Knight::new),
    PAWN("p", 1, Pawn::new),
    KING("k", 0, King::new),
    NONE(".", 0, color -> new EmptySpace());

    private final String symbol;
    private final double score;
    private final Function<Color, Piece> creater;

    PieceType(String symbol, double score, Function<Color, Piece> creater) {
        this.symbol = symbol;
        this.score = score;
        this.creater = creater;
    }

    public Piece createPiece(Color color) {
        return creater.apply(color);
    }

    public double calculateScore(int count) {
        return score * count;
    }

    public String getSymbol() {
        return symbol;
    }
}
