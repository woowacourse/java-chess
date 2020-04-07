package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.NotPawn;
import chess.domain.piece.team.Team;

import java.util.List;

public class Bishop extends NotPawn {
    private Bishop(BishopBuilder builder) {
        super(builder);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }

        Piece exPiece = board.getPiece(to);
        MoveType moveType = this.moveType.update(this, exPiece);
        return new BishopBuilder(name, to, team, canNotMoveStrategies, score)
                .moveType(moveType)
                .build();
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        if (position.isNotStraightDiagonalDirection(to)) {
            return true;
        }

        return hasHindranceDiagonallyInBetween(to, board);
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
