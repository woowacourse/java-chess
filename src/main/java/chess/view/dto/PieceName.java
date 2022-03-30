package chess.view.dto;

import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public enum PieceName {
    PAWN("P", "p"),
    ROOK("R", "r"),
    KNIGHT("N", "n"),
    BISHOP("B", "b"),
    QUEEN("Q", "q"),
    KING("K", "k");

    public final String blackName;
    public final String whiteName;

    PieceName(String blackName, String whiteName) {
        this.blackName = blackName;
        this.whiteName = whiteName;
    }

    public static PieceName find(PieceType pieceType) {
        return Stream.of(PieceName.values())
                .filter(pieceName -> pieceName.name().equals(pieceType.name()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 말 타입입니다."));
    }

    public String getName(PieceColor pieceColor) {
        if (pieceColor == PieceColor.BLACK) {
            return blackName;
        }

        return whiteName;
    }
}
