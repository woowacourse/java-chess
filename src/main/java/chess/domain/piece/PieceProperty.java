package chess.domain.piece;

import chess.domain.Camp;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceProperty {
    BISHOP("b", 3, Bishop::new),
    KING("k", 0, King::new),
    KNIGHT("n", 2.5, Knight::new),
    PAWN("p", 1, Pawn::new),
    QUEEN("q", 9, Queen::new),
    ROOK("r", 5, Rook::new),
    NULL_PIECE(".", 0, camp -> new NullPiece(null)),
    ;

    private final String character;
    private final double score;
    private final Function<Camp, Piece> generatePiece;

    PieceProperty(final String value, final double score,
                  final Function<Camp, Piece> generatePiece) {
        this.character = value;
        this.score = score;
        this.generatePiece = generatePiece;
    }

    public static Piece createPieceWith(final String piece, final Camp camp) {
        return Arrays.stream(PieceProperty.values())
            .filter(pieceEnum -> pieceEnum.getCharacter().equals(piece))
            .findFirst()
            .orElse(NULL_PIECE)
            .getGeneratePiece().apply(camp);
    }

    public String getCharacter() {
        return character;
    }

    public double getScore() {
        return score;
    }

    public Function<Camp, Piece> getGeneratePiece() {
        return generatePiece;
    }
}
