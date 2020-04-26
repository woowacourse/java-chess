package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.List;

public class MovedPawn extends Initialized {
    private static final Score SCORE_WITH_PEER = new Score(0.5);
    public static final double MAX_DISTANCE = Math.sqrt(2);

    private MovedPawn(MovedPawnBuilder builder) {
        super(builder);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        MoveType moveType = this.moveType.update(this, exPiece);
        return new MovedPawnBuilder(name, team, canNotMoveStrategies, score)
                .moveType(moveType)
                .build();
    }

    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return false;
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
//        if (hasPeerOnSameCollumn(piecesState)) {
//            return SCORE_WITH_PEER;
//        }
        return score;
    }

    public static class MovedPawnBuilder extends InitializedBuilder {

        public MovedPawnBuilder(String name, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
            super(name, team, canNotMoveStrategies, score);
        }

        @Override
        public Piece build() {
            return new MovedPawn(this);
        }

    }
}
