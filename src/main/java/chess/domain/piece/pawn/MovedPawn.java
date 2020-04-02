package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.Pawn;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class MovedPawn extends Pawn {


    public static final int MAX_DISTANCE = 1;

    public MovedPawn(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
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
        Position forwardPositoin = position.go(team.getForwardDirection());
        Piece forward = board.getPiece(forwardPositoin);
        return forward.isNotBlank();
    }
}
