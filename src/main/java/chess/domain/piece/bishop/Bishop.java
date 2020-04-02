package chess.domain.piece.bishop;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.state.NotPawn;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class Bishop extends NotPawn {

    public Bishop(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }
        return new Bishop(name, to, team, canNotMoveStrategies, score);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        if (position.isNotStraightDiagonalDirection(to)) {
            return true;
        }

        return hasHindranceDiagonallyInBetween(to, board);
    }
}
