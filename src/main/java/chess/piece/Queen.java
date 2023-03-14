package chess.piece;

import chess.Position;

import java.util.List;

public class Queen extends ChessPiece{

    public Queen(final Team team) {
        super(team);
    }

    public static List<Position> initialPosition(Team team) {
        if (team == Team.WHITE) {
            return List.of(new Position(4, 1));
        }

        return List.of(new Position(4, 8));
    }
}
