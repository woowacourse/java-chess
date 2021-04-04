package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.chess.Color;
import chess.domain.position.MovePosition;

public class Rook extends AbstractPiece {

    public static final Rook BLACK_INSTANCE = new Rook(Color.BLACK);
    public static final Rook WHITE_INSTANCE = new Rook(Color.WHITE);

    private static final DirectionGroup DIRECTION_GROUP;
    private static final String SYMBOL = "r";
    private static final String NAME = "Rook";
    private static final double SCORE = 5;

    static {
        final int MOVABLE_LENGTH = 7;
        DIRECTION_GROUP = new DirectionGroup(Direction.linearDirection(), MOVABLE_LENGTH);
    }

    private Rook(Color color) {
        super(color, DIRECTION_GROUP);
    }

    public static Rook from(Color color) {
        if (color.isBlack()) {
            return BLACK_INSTANCE;
        }

        return WHITE_INSTANCE;
    }

    @Override
    public void checkToMoveToTargetPosition(MovePosition movePosition, Board board) {
        Direction direction = DIRECTION_GROUP.findDirection(movePosition);
        checkObstacleExistsAtDirection(movePosition, direction, board);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public String getSymbol() {
        return changeColorSymbol(SYMBOL);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
