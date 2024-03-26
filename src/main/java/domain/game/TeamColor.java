package domain.game;

import java.util.Set;

import static domain.game.PieceType.*;

public enum TeamColor {
    BLACK(Set.of(BLACK_KING, BLACK_QUEEN, BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_PAWN)),
    WHITE(Set.of(WHITE_KING, WHITE_QUEEN, WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_PAWN));

    private final Set<PieceType> includedPieces;

    TeamColor(Set<PieceType> includedPieces) {
        this.includedPieces = includedPieces;
    }

    public boolean contains(PieceType pieceType) {
        return includedPieces.contains(pieceType);
    }
}
