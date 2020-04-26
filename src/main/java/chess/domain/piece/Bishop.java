package chess.domain.piece;

import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.Initialized;
import chess.domain.piece.team.Team;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Initialized {
    private Bishop(BishopBuilder builder) {
        super(builder);
    }

    @Override
    public Piece move(Position to, Piece exPiece) {
        MoveType moveType = this.moveType.update(this, exPiece);
        return new BishopBuilder(name, team, canNotMoveStrategies, score)
                .moveType(moveType)
                .build();
    }

//    public static boolean canNotMove(Position from, Position to, PiecesState piecesState) {
//        //todo: refac
//        List<CanNotMoveStrategy> canNotMoveStrategies = new ArrayList<>();
////                getCanNotMoveStrategiesOf(piece.getClass());
//        return canNotMoveStrategies.stream()
//                .anyMatch(canNotMoveStrategy -> canNotMoveStrategy.canNotMove(from, to, piecesState));
//    }

    @Override
    public boolean canNotMove(Position from, Position to, PiecesState piecesState) {
        return false;
    }

    @Override
    public Score calculateScore(PiecesState piecesState) {
        return score;
    }

    public static class BishopBuilder extends InitializedBuilder {
        public BishopBuilder(String name, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
            super(name,  team, canNotMoveStrategies, score);
        }

        @Override
        public Piece build() {
            return new Bishop(this);
        }
    }
}
