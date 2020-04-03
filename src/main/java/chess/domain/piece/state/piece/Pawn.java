package chess.domain.piece.state.piece;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public abstract class Pawn extends Initialized {
    private static final Score SCORE_WITH_PEER = new Score(0.5);
    private static final int LINE_START_INDEX = 1;
    private static final int LINE_END_INDEX = 8;

    protected Pawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    public Pawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score, MoveType moveType) {
        super(name, position, team, canNotMoveStrategies, score, moveType);
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
