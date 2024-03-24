package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.DirectionJudge;
import chess.domain.position.Position;

import java.util.List;

import static chess.domain.position.Direction.*;

public class Rook extends Piece {
    private static final List<Direction> PASSING = List.of(N, S, E, W);

    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        try {
            return PASSING.contains(DirectionJudge.judge(start, destination))
                    && board.pathIsAllEmpty(start.findPath(destination));
        } catch (IllegalStateException e) {
            return false;
        }
    }
}
