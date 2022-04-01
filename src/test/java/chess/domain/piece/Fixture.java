package chess.domain.piece;

import chess.domain.board.Position;

public class Fixture {
    public static final Position E4 = Position.from("E4");
    public static final Bishop BISHOP_WHITE = new Bishop(Color.WHITE);
}
