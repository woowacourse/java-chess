package chess.view;

import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class CharacterViewer {
    public static String convertToString(Team team, Kind kind) {
        String kindValue = convertKindToString(kind);
        if (Team.WHITE == team) {
            return kindValue;
        }
        return kindValue.toUpperCase();
    }

    private static String convertKindToString(Kind kind) {
        return switch (kind) {
            case PAWN -> "p";
            case KNIGHT -> "n";
            case BISHOP -> "b";
            case ROOK -> "r";
            case QUEEN -> "q";
            case KING -> "k";
        };
    }
}
