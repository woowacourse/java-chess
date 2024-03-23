package chess.view;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class CharacterViewer {
    public static String convertToString(Character character) {
        String kindValue = convertKindToString(character.kind());
        if (Team.WHITE == character.team()) {
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
