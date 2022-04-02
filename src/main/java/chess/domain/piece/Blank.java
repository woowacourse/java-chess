package chess.domain.piece;

import chess.domain.Team;
import chess.domain.board.Position;

public class Blank extends Piece {

    private static final String SYMBOL = ".";
    private static final String CAN_NOT_MOVE_ERROR = "빈 곳은 이동이 불가능 합니다.";
    private static final double NO_SCORE = 0;

    public Blank() {
        super(Team.NEUTRALITY);
    }

    @Override
    protected String createSymbol(final Team team) {
        return SYMBOL;
    }

    @Override
    public void checkReachable(final Piece targetPiece, final Position source, final Position target) {
        throw new IllegalArgumentException(CAN_NOT_MOVE_ERROR);
    }

    @Override
    public boolean isBlank() {
        return true;
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
    public double getScore() {
        return NO_SCORE;
    }

    @Override
    public String fileName() {
        return "blank.png";
    }
}
