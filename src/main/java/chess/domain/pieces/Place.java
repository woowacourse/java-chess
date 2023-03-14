package chess.domain.pieces;

import chess.domain.board.Position;

public class Place extends Piece{

    public Place(final Position position) {
        super(position);
    }

    @Override
    public void move(final String position) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }
}
