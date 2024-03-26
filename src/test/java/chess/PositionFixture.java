package chess;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public enum PositionFixture {
    WHITE_PAWN_FIRST_MOVE_POSITION(new Position(File.A, Rank.TWO)),
    BLACK_PAWN_FIRST_MOVE_POSITION(new Position(File.A, Rank.SEVEN)),
    PAWN_NOT_FIRST_MOVE_POSITION(new Position(File.A, Rank.FIVE)),
    ;

    private final Position position;

    PositionFixture(final Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
