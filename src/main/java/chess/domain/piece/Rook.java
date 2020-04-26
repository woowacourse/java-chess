package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.List;

public class Rook extends Initialized {
    private Rook(RookBuilder builder) {
        super(builder);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        MoveType moveType = this.moveType.update(this, exPiece);
        return new RookBuilder(name, team, canNotMoveStrategies, score)
                .moveType(moveType)
                .build();
    }

    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return false;
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
        return score;
    }

    public static class RookBuilder extends InitializedBuilder {
        public RookBuilder(String name, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
            super(name, team, canNotMoveStrategies, score);
        }

        @Override
        public Piece build() {
            return new Rook(this);
        }
    }
}
