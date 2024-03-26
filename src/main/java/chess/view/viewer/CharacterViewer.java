package chess.view.viewer;

import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class CharacterViewer {
    public static String showKind(Team team, Kind kind) {
        String kindView = showKind(kind);
        if (Team.WHITE == team) {
            return kindView;
        }
        return kindView.toUpperCase();
    }

    private static String showKind(Kind kind) {
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
