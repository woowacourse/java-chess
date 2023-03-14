package chess.piece;

import chess.Position;

import java.util.List;

public class Bishop extends ChessPiece{

    public Bishop(final Team team) {
        super(team);
    }

    public static List<Position> initialPosition(Team team) {
        if (team == Team.WHITE) {
            return List.of(new Position(3, 1),
                           new Position(6,1));
        }
        return List.of(new Position(3, 8),
                       new Position(6,8));
    }
}
