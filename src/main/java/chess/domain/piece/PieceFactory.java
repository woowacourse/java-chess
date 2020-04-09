package chess.domain.piece;

import chess.domain.exception.WrongOperationException;
import chess.domain.piece.pieces.PieceInitializer;
import chess.domain.position.Position;

import java.util.Arrays;

public class PieceFactory {
    public static Piece of(String name, Position position) {
        if (name.equals(".")) {
            return new Blank();
        }

        String nameForCheck = name.toUpperCase();
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.getResource().equals(nameForCheck))
                .map(pieceType -> new Piece(position, pieceType, PieceInitializer.findPieceMovable(pieceType, findColor(name)), findColor(name)))
                .findFirst()
                .orElseThrow(WrongOperationException::new);
    }

    private static Color findColor(String name) {
        if (name.equals(name.toUpperCase())) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }
}
