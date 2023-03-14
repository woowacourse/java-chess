package chess.piece;

import chess.Position;

import java.util.List;

public class Rook extends ChessPiece{
    public Rook(final Team team) {
        super(team);
    }

    public static List<Position> initialPosition(Team team) {
        if (team == Team.WHITE) {
            return List.of(new Position(1, 1),
                           new Position(8,1));
        }
        return List.of(new Position(5, 8),
                       new Position(8,8));
    }
}
