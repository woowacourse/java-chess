package chess.utils;

import chess.domain.AbstractPiece;
import chess.domain.Position;
import chess.domain.Team;

public class WebUtils {
    public static String positionParser(Position position) {
        return position.getX() + position.getY();
    }

    public static String caseChanger(AbstractPiece abstractPiece) {
        if (abstractPiece.getTeam() == Team.BLACK) {
            return abstractPiece.getName().toUpperCase();
        }
        return abstractPiece.getName();
    }
}
