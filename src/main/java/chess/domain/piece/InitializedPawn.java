package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.piece.Pawn;
import chess.domain.piece.team.Team;
import chess.domain.piece.position.Position;

import java.util.List;

//todo: add tests
public class InitializedPawn extends Pawn {
    public static final int MAX_DISTANCE = 2;

    public InitializedPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }

        return PieceFactory.createPiece(MovedPawn.class, to, team);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        if (isHeadingHeadingDiagonal(to)) {
            return false;
        }

        return hasHindrance(board);
    }

    private boolean hasHindrance(Board board) {
        Position forwardPosition = position;
        for (int i = 0; i < MAX_DISTANCE; i++) {
            forwardPosition = forwardPosition.go(team.getForwardDirection());
            Piece forward = board.getPiece(forwardPosition);
            if (forward.isNotBlank()) {
                return true;
            }
        }
        return false;


    }
}
