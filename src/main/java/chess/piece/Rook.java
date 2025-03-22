package chess.piece;

import chess.Color;
import chess.Position;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(final Position position) {
        if (this.position.row().compareTo(position.row()) != 0 &&
            this.position.column().compareTo(position.column()) != 0) {
            throw new IllegalArgumentException("[ERROR] 룩이 이동할 수 없는 경로입니다.");
        }

        this.position = position;
    }

    public Position position() {
        return position;
    }

    @Override
    public String name() {
        return "Rook";
    }
}
