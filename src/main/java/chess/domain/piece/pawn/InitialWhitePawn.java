package chess.domain.piece.pawn;

import static chess.domain.board.Weight.ONE_LEFT_ONE_UP;
import static chess.domain.board.Weight.ONE_RIGHT_ONE_UP;
import static chess.domain.board.Weight.ONE_UP;
import static chess.domain.board.Weight.TWO_UP;
import static chess.domain.piece.pawn.NormalWhitePawn.NORMAL_WHITE_PAWN;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

public class InitialWhitePawn extends Pawn {
    public static final Piece INITIAL_WHITE_PAWN = new InitialWhitePawn();

    private InitialWhitePawn() {
        super(Team.WHITE);
    }

    @Override
    Set<Entry<Integer, Integer>> straightWeights() {
        return new LinkedHashSet<>(
                Arrays.asList(
                        ONE_UP.value(),
                        TWO_UP.value()
                )
        );
    }

    Set<Entry<Integer, Integer>> diagonalWeights() {
        return Set.of(
                ONE_RIGHT_ONE_UP.value(),
                ONE_LEFT_ONE_UP.value()
        );
    }

    @Override
    public Piece updateAfterMove() {
        return NORMAL_WHITE_PAWN;
    }
}
