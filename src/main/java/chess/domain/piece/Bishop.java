package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.List;

public class Bishop extends Initialized {
    private Bishop(BishopBuilder builder) {
        super(builder);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        MoveType moveType = this.moveType.update(this, exPiece);
        return new BishopBuilder(name, to, team, canNotMoveStrategies, score)
                .moveType(moveType)
                .build();
    }

    @Override
    public boolean hasHindrance(Position to, PiecesState piecesState) {
        if (position.isNotStraightDiagonalDirection(to)) {
            return true;
        }

        return hasHindranceDiagonallyInBetween(to, piecesState);
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
        return score;
    }

    public static class BishopBuilder extends InitializedBuilder {
        public BishopBuilder(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
            super(name, position, team, canNotMoveStrategies, score);
        }

        @Override
        public Piece build() {
            return new Bishop(this);
        }
    }
}
