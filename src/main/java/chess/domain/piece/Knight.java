package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.knight.KnightDirection;

import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public void move(Board ignored, Point from, Point to) {
        List<Point> candidates = KnightDirection.createNextPointCandidates(from);
        if (!candidates.contains(to)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 경로입니다.");
        }
    }
}
