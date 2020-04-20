package chess.domain.piece;

import chess.config.BoardConfig;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class MovedPawn extends Initialized {
    private static final Score SCORE_WITH_PEER = new Score(0.5);
    public static final double MAX_DISTANCE = Math.sqrt(2);

    private MovedPawn(MovedPawnBuilder builder) {
        super(builder);
    }

//    @Override
//    public Piece move(Position to, Board board) {
//        if (canNotMove(to, board)) {
//            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
//        }
//        Piece exPiece = board.getPiece(to);
//        MoveType moveType = this.moveType.update(this, exPiece);
//        return new MovedPawnBuilder(name, to, team, canNotMoveStrategies, score)
//                .moveType(moveType)
//                .build();
//    }

    @Override
    public boolean hasHindrance(Position to, PiecesState piecesState) {
        return hasHindranceStraightInBetween(to, piecesState);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        MoveType moveType = this.moveType.update(this, exPiece);
        return new MovedPawnBuilder(name, to, team, canNotMoveStrategies, score)
                .moveType(moveType)
                .build();
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
        if (hasPeerOnSameCollumn(piecesState)) {
            return SCORE_WITH_PEER;
        }
        return score;
    }

    public static class MovedPawnBuilder extends InitializedBuilder {

        public MovedPawnBuilder(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
            super(name, position, team, canNotMoveStrategies, score);
        }

        @Override
        public Piece build() {
            return new MovedPawn(this);
        }

    }

    private boolean hasPeerOnSameCollumn(PiecesState piecesState) {
        int collumn = position.getX();
        return IntStream.rangeClosed(BoardConfig.LINE_START, BoardConfig.LINE_END)
                .mapToObj(row -> piecesState.getPiece(Position.of(collumn, row)))
                .anyMatch(hasPeerOnSameCollumn());
    }

    private Predicate<Piece> hasPeerOnSameCollumn() {
        return piece -> (piece instanceof MovedPawn || piece instanceof InitializedPawn)
                && !this.equals(piece)
                && this.isSameTeam(piece);
    }
}
