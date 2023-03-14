package chess.piece;

import chess.Position;

import java.util.List;

public class King extends ChessPiece {

    private King(final Team team) {
        super(team);
    }

    public static List<Position> initialPosition(Team team) {
        if (team == Team.WHITE) {
            return List.of(new Position(5, 1));
        }

        return List.of(new Position(5, 8));
    }
}
