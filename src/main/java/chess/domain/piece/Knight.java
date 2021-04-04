package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.chess.Color;
import chess.domain.position.MovePosition;

public class Knight extends AbstractPiece {

    public static final Knight BLACK_INSTANCE = new Knight(Color.BLACK);
    public static final Knight WHITE_INSTANCE = new Knight(Color.WHITE);

    private static final DirectionGroup DIRECTION_GROUP;
    private static final String SYMBOL = "n";
    private static final String NAME = "KNIGHT";
    private static final double SCORE = 2.5;

    static {
        final int MOVABLE_LENGTH = 1;
        DIRECTION_GROUP = new DirectionGroup(Direction.knightDirection(), MOVABLE_LENGTH);
    }

    private Knight(Color color) {
        super(color, DIRECTION_GROUP);
    }

    public static Knight from(Color color) {
        if (color.isBlack()) {
            return BLACK_INSTANCE;
        }

        return WHITE_INSTANCE;
    }

    @Override
    public void checkToMoveToTargetPosition(MovePosition movePosition, Board board) {
        DIRECTION_GROUP.findDirection(movePosition);
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
