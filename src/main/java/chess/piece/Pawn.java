package chess.piece;

import chess.Position;

import java.util.List;

public class Pawn extends ChessPiece{

    public Pawn(final Team team) {
        super(team);
    }

    public static List<Position> initialPosition(Team team) {
        if (team == Team.WHITE) {
            return List.of(new Position(1,2),
                           new Position(2,2),
                           new Position(3,2),
                           new Position(4,2),
                           new Position(5,2),
                           new Position(6,2),
                           new Position(7,2),
                           new Position(8,2));
        }
        return List.of(new Position(1,7),
                       new Position(2,7),
                       new Position(3,7),
                       new Position(4,7),
                       new Position(5,7),
                       new Position(6,7),
                       new Position(7,7),
                       new Position(8,7));
    }
}
