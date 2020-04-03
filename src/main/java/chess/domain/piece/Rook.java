package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.policy.move.CanNotMoveStrategy;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.state.piece.NotPawn;
import chess.domain.piece.team.Team;

import java.util.List;

public class Rook extends NotPawn {
    public Rook(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    public Rook(String name, Position to, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score, MoveType moveType) {
        super(name, to, team, canNotMoveStrategies, score, moveType);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }
        Piece exPiece = board.getPiece(to);
        moveType = moveType.update(this, exPiece);
        return new Rook(name, to, team, canNotMoveStrategies, score, moveType);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        return hasHindrancePerpendicularlyInBetween(to, board);
    }
}
