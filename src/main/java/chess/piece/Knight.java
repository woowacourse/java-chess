package chess.piece;

import chess.Position;

import java.util.List;

public class Knight extends ChessPiece {

    public Knight(final Team team) {
        super(team);
    }

    public static List<Position> initialPosition(Team team) {
        if (team == Team.WHITE) {
            return List.of(new Position(2, 1),
                           new Position(7, 1));
        }
        return List.of(new Position(2, 8),
                       new Position(7, 8));
    }
}
