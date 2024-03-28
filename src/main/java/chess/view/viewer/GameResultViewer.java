package chess.view.viewer;

import chess.domain.GameResult;
import chess.domain.piece.character.Team;

public class GameResultViewer {
    public static String show(GameResult gameResult) {
        return switch (gameResult) {
            case WHITE_WIN -> TeamViewer.show(Team.WHITE) + " 승리!";
            case BLACK_WIN -> TeamViewer.show(Team.BLACK) + " 승리!";
            case DRAW -> "무승부!";
        };
    }
}
