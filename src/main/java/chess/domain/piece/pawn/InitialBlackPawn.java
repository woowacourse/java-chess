package chess.domain.piece.pawn;

import static chess.domain.board.Weight.ONE_DOWN;
import static chess.domain.board.Weight.ONE_LEFT_ONE_DOWN;
import static chess.domain.board.Weight.ONE_RIGHT_ONE_DOWN;
import static chess.domain.board.Weight.TWO_DOWN;
import static chess.domain.piece.pawn.NormalBlackPawn.NORMAL_BLACK_PAWN;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

public class InitialBlackPawn extends Pawn {

    public static final Piece INITIAL_BLACK_PAWN = new InitialBlackPawn();

    private InitialBlackPawn() {
        super(Team.BLACK);
    }

    @Override
    Set<Entry<Integer, Integer>> straightWeights() {
        return new LinkedHashSet<>(
                Arrays.asList(
                        ONE_DOWN.value(),
                        TWO_DOWN.value()
                )
        );
    }

    Set<Entry<Integer, Integer>> diagonalWeights() {
        return Set.of(
                ONE_RIGHT_ONE_DOWN.value(),
                ONE_LEFT_ONE_DOWN.value()
        );
    }

    @Override
    public Piece updateAfterMove() {
        return NORMAL_BLACK_PAWN;
    }
}
