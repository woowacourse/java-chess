package chess.piece;

import chess.Color;
import chess.Position;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public void move(Position end) {
        int rowabs = Math.abs(this.position.row().ordinal() - position.row().ordinal());
        int colabs = Math.abs(this.position.column().ordinal() - position.column().ordinal());

        if (rowabs != colabs || (this.position.row().compareTo(position.row()) != 0 &&
                this.position.column().compareTo(position.column()) != 0)) {
            throw new IllegalArgumentException("[ERROR] 퀸이 이동할 수 없습니다.");
        }

        this.position = end;
    }

    @Override
    public String name() {
        return "Queen";
    }
}
