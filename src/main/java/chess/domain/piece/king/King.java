package chess.domain.piece.king;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.NotPawn;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

public class King extends NotPawn {

    public static final double MAX_DISTANCE = Math.sqrt(2);

    public King(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies, Score score) {
        super(name, position, team, canNotMoveStrategies, score);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            throw new IllegalArgumentException(String.format("%s 위치의 말을 %s 위치로 옮길 수 없습니다.", position, to));
        }
        return new King(name, to, team, canNotMoveStrategies, score);
    }

    @Override
    public boolean hasHindrance(Position to, Board board) {
        return false;
    }
}
