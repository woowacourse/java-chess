package chess.domain.piece;

import chess.domain.board.Direction;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Queen extends DirectionMovePiece {

    public Queen(Team team) {
        super(PieceType.QUEEN, team);
    }

    @Override
    Set<Direction> legalDirections() {
        return Arrays.stream(Direction.values())
                .collect(Collectors.toSet());
    }
}
