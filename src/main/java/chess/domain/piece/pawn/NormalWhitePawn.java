package chess.domain.piece.pawn;

import static chess.domain.board.Weight.ONE_LEFT_ONE_UP;
import static chess.domain.board.Weight.ONE_RIGHT_ONE_UP;
import static chess.domain.board.Weight.ONE_UP;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Map.Entry;
import java.util.Set;

public class NormalWhitePawn extends Pawn {
    public static final Piece NORMAL_WHITE_PAWN = new NormalWhitePawn();

    private NormalWhitePawn() {
        super(Team.WHITE);
    }

    @Override
    Set<Entry<Integer, Integer>> straightWeights() {
        return Set.of(ONE_UP.value());
    }

    Set<Entry<Integer, Integer>> diagonalWeights() {
        return Set.of(
                ONE_RIGHT_ONE_UP.value(),
                ONE_LEFT_ONE_UP.value()
        );
    }

    @Override
    public Piece updateAfterMove() {
        return this;
    }
}
