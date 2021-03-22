package chess.domain.piece;

public class Empty extends Piece {
    public static final Empty EMPTY = new Empty(Position.EMPTY, ".", Color.NONE);

    public Empty(Position position, String name, Color color) {
        super(position, name, color);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        throw new IllegalArgumentException("[ERROR] 기물이 존재하지 않습니다.");
    }
}
