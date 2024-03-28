package chess.view.viewer;

import chess.domain.piece.character.Team;

public class TeamViewer {
    public static String show(Team team) {
        return switch (team) {
            case BLACK -> "흑팀";
            case WHITE -> "백팀";
        };
    }
}
