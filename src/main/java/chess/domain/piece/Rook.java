package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public final class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        return findDirection(src, dest) != null;
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        List<Direction> directions = Direction.getRookDirections();

        for (Direction direction : directions) {
            // 언제까지 반복해야하는가?
            // 체스판 범위를 벗어나면 멈춤 -> move에서 에러가 날듯
            // dest을 만나면 OK
            for (int i = 1; i < 8; i++) {
                int x = direction.getX() * i;
                int y = direction.getY() * i;
                // 체스판 벗어나면 break;
                try {
                    if (dest.equals(src.move(x, y))) {
                        return direction;
                    }
                } catch (IllegalArgumentException e) {
                    break;
                }
            }
        }
        return null;
    }
}
