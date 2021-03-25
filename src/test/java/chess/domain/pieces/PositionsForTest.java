package chess.domain.pieces;

import chess.domain.position.Position;

public class PositionsForTest {
    public static final Position whiteTeamPawnPosition = new Position(0, 2);
    public static final Position crossBlackTeamPawnPosition = new Position(0, 0);
    public static final Position straightBlackTeamPawnPosition = new Position(1, 0);
    public static final Position crossBlankPosition = new Position(2, 0);
    public static final Position straightBlankPosition = new Position(2, 1);

    public static final Position straightCrossBlackTeamPawnPosition = new Position(3, 0);
    public static final Position straightCrossBlankPosition = new Position(0, 3);
}
