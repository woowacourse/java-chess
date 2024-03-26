package chess.domain.piece.pawn;

import static chess.domain.piece.pawn.NormalBlackPawn.NORMAL_BLACK_PAWN;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
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
                        Map.entry(0, -1),
                        Map.entry(0, -2)
                )
        );
    }

    @Override
    Set<Entry<Integer, Integer>> diagonalWeights() {
        return Set.of(
                Map.entry(-1, -1),
                Map.entry(1, -1)
        );
    }

    @Override
    public Piece updateAfterMove() {
        return NORMAL_BLACK_PAWN;
    }
}
