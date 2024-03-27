package chess.domain.piece.pawn;

import static chess.domain.board.Weight.ONE_DOWN;
import static chess.domain.board.Weight.ONE_LEFT_ONE_DOWN;
import static chess.domain.board.Weight.ONE_RIGHT_ONE_DOWN;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Map.Entry;
import java.util.Set;

public class NormalBlackPawn extends Pawn {
    public static final Piece NORMAL_BLACK_PAWN = new NormalBlackPawn();

    private NormalBlackPawn() {
        super(Team.BLACK);
    }

    @Override
    Set<Entry<Integer, Integer>> straightWeights() {
        return Set.of(ONE_DOWN.value());
    }

    Set<Entry<Integer, Integer>> diagonalWeights() {
        return Set.of(
                ONE_RIGHT_ONE_DOWN.value(),
                ONE_LEFT_ONE_DOWN.value()
        );
    }

    @Override
    public Piece updateAfterMove() {
        return this;
    }
}
