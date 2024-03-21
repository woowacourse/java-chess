package chess.view;

import chess.domain.piece.Team;
import chess.domain.piece.PieceType;

public enum PieceMapper {

    KING('k'),
    QUEEN('q'),
    ROOK('r'),
    BISHOP('b'),
    KNIGHT('n'),
    PAWN('p');

    private final char name;

    PieceMapper(char name) {
        this.name = name;
    }

    public static char map(PieceType pieceType, Team team) {
        if (team == Team.BLACK) {
            return Character.toUpperCase(valueOf(pieceType.name()).name);
        }
        return valueOf(pieceType.name()).name;
    }
}
