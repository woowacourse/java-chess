package chess.domain.piece;

import chess.config.BoardConfig;
import chess.domain.board.Board;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InitializedPawn extends Initialized {
    private static final Score SCORE_WITH_PEER = new Score(0.5);
    public static final int MAX_DISTANCE = 2;

    private InitializedPawn(InitializedPawnBuilder builder) {
        super(builder);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }

        Piece exPiece = board.getPiece(to);
        MoveType moveType = this.moveType.update(this, exPiece);

        return new MovedPawn.MovedPawnBuilder(name, to, team, canNotMoveStrategies, score)
                .moveType(moveType)
                .build();
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        if (isHeadingHeadingDiagonal(to)) {
            return false;
        }

        return hasHindrance(board);
    }

    @Override
    public Score calculateScore(Board board) {
        if (hasPeerOnSameCollumn(board)) {
            return SCORE_WITH_PEER;
        }
        return score;
    }

    public static class InitializedPawnBuilder extends InitializedBuilder {
        public InitializedPawnBuilder(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
            super(name, position, team, canNotMoveStrategies, score);
        }

        @Override
        public Piece build() {
            return new InitializedPawn(this);
        }
    }

    private boolean hasPeerOnSameCollumn(Board board) {
        int collumn = position.getX();
        return IntStream.rangeClosed(BoardConfig.LINE_START, BoardConfig.LINE_END)
                .mapToObj(row -> board.getPiece(Position.of(collumn, row)))
                .anyMatch(hasPeerOnSameCollumn());
    }

    private Predicate<Piece> hasPeerOnSameCollumn() {
        return piece -> (piece instanceof InitializedPawn || piece instanceof MovedPawn)
                && !this.equals(piece)
                && this.isSameTeam(piece);
    }

    private boolean hasHindrance(Board board) {
        Position forwardPosition = position.go(team.getForwardDirection());
        return Stream.iterate(forwardPosition, position -> position.go(team.getForwardDirection()))
                .limit(MAX_DISTANCE)
                .map(board::getPiece)
                .anyMatch(Piece::isNotBlank);
    }
}
