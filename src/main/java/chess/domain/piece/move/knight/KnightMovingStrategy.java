package chess.domain.piece.move.knight;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.MovingStrategy;

public class KnightMovingStrategy implements MovingStrategy {

    @Override
    public boolean move(Board board, Point from, Point to) {
        List<Point> candidates = KnightDirection.createNextPointCandidates(from);
        return candidates.contains(to);
    }
}
