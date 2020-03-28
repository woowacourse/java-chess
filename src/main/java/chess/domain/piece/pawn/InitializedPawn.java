package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

//todo: add tests
public class InitializedPawn extends Pawn {
    public static final int MAX_DISTANCE = 2;

    public InitializedPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        super(name, position, team, canNotMoveStrategies);
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
        if (isHeadingDiagonal(to)) {
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
