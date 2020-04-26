package chess.domain.piece;

import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.List;
import java.util.function.Predicate;

public class InitializedPawn extends Initialized {
    private static final Score SCORE_WITH_PEER = new Score(0.5);
    public static final int MAX_DISTANCE = 2;

    private InitializedPawn(InitializedPawnBuilder builder) {
        super(builder);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        MoveType moveType = this.moveType.update(this, exPiece);
        return PieceFactory.createMovedPiece(PieceType.MOVED_PAWN, team, moveType);
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

    public static class InitializedPawnBuilder extends InitializedBuilder {
        public InitializedPawnBuilder(String name, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
            super(name, team, canNotMoveStrategies, score);
        }

        @Override
        public Piece build() {
            return new InitializedPawn(this);
        }
    }

    private Predicate<Piece> hasPeerOnSameCollumn() {
        return piece -> (piece instanceof InitializedPawn || piece instanceof MovedPawn)
                && !this.equals(piece)
                && this.isSameTeam(piece);
    }
}
