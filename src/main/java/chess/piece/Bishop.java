package chess.piece;

import chess.Color;
import chess.Position;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(final Position position) {
        int rowabs = Math.abs(this.position.row().ordinal() - position.row().ordinal());
        int colabs = Math.abs(this.position.column().ordinal() - position.column().ordinal());

        if (rowabs != colabs) {
            throw new IllegalArgumentException("[ERROR] 비숍이 이동할 수 없습니다.");
        }

        this.position = position;
    }

    public Position position() {
        return position;
    }

    @Override
    public String name() {
        return "Bishop";
    }
}
