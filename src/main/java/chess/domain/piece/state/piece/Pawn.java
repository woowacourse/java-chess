package chess.domain.piece.state.piece;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public abstract class Pawn extends Initialized {
    private static final Score SCORE_WITH_PEER = new Score(0.5);
    private static final int LINE_START_INDEX = 1;
    private static final int LINE_END_INDEX = 8;

    protected Pawn(InitializedBuilder builder) {
        super(builder);
    }

    @Override
    public Score calculateScore(Board board) {
        if (hasPeerOnSameCollumn(board)) {
            return SCORE_WITH_PEER;
        }
        return score;
    }

    private boolean hasPeerOnSameCollumn(Board board) {
        int collumn = position.getX();
        return IntStream.rangeClosed(LINE_START_INDEX, LINE_END_INDEX)
                .mapToObj(row -> board.getPiece(Position.of(collumn, row)))
                .anyMatch(hasPeerOnSameCollumn());
    }

    private Predicate<Piece> hasPeerOnSameCollumn() {
        return piece -> piece instanceof Pawn && !this.equals(piece) && this.isSameTeam(piece);
    }
}
