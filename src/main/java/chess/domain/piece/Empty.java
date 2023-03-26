package chess.domain.piece;

import chess.domain.*;

public class Empty extends ChessPiece {

    public Empty() {
        super(Color.BLANK);
    }

    @Override
    Shape selectShape(Color color) {
        return Shape.BLANK;
    }

    @Override
    Direction findMovableDirection(Position sourcePosition, Position targetPosition) {
        throw new IllegalArgumentException("[ERROR] Empty 객체는 이동 방향을 가질 수 없습니다.");
    }

    @Override
    int findDistance(Direction direction, Position sourcePosition, Position targetPosition) {
        throw new IllegalArgumentException("[ERROR] Empty 객체는 이동 거리를 가질 수 없습니다.");
    }

    @Override
    public boolean isMovable(Movement movement, ChessBoard chessBoard) {
        throw new IllegalArgumentException("[ERROR] Empty 객체는 이동 가능 여부를 판단할 수 없습니다.");
    }
}
