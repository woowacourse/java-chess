package chess.domain.pieces;

import chess.domain.board.Position;

public class Empty extends Piece {

    public Empty(Team team) {
        super(team);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        throw new IllegalArgumentException("움직일 수 없는 말입니다.");
    }
}
