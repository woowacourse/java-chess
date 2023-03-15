package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Move;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends Piece {

    private Pawn(boolean isWhite, Set<Move> moves) {
        super(isWhite, moves);
    }

    public static Pawn from(boolean isWhite) {
        Set<Move> whiteMoves = Set.of(new Move(List.of(Direction.UP)), new Move(List.of(Direction.UP, Direction.UP)));
        if (isWhite) {
            return new Pawn(true, whiteMoves);
        }
        Set<Move> blackMoves = whiteMoves.stream()
                .map(Move::flipHorizontal)
                .collect(Collectors.toSet());
        return new Pawn(false, blackMoves);
    }
}
