package chess.domain.piece.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class InitialBlackPawn extends Pawn {
    public InitialBlackPawn() {
        super(Team.BLACK);
    }

    @Override
    Set<Entry<Integer, Integer>> straightWeights() {
        return Set.of(
                Map.entry(0, -1),
                Map.entry(0, -2)
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
        return new NormalBlackPawn();
    }
}
