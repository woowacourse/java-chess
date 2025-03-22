package chess.view;

import chess.board.Color;
import chess.piece.PieceType;
import java.util.Arrays;

public enum PieceNotation {

    BISHOP('b', PieceType.BISHOP),
    KING('k', PieceType.KING),
    KNIGHT('n', PieceType.KNIGHT),
    PAWN('p', PieceType.PAWN),
    QUEEN('q', PieceType.QUEEN),
    ROOk('r', PieceType.ROOk);

    private final Character notation;
    private final PieceType pieceType;

    PieceNotation(Character notation, PieceType pieceType) {
        this.notation = notation;
        this.pieceType = pieceType;
    }

    public static Character of(Color color, PieceType targetPieceType) {
        PieceNotation targetNotation = Arrays.stream(PieceNotation.values())
                .filter(pieceNotation -> pieceNotation.getPieceType() == targetPieceType)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 기물의 표기법을 찾을 수 없습니다."));
        if (color.isBlack()) {
            return Character.toUpperCase(targetNotation.getNotation());
        }
        // white
        return Character.toLowerCase(targetNotation.getNotation());
    }

    public Character getNotation() {
        return notation;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
