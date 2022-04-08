package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.Position;

import java.util.Collections;
import java.util.List;

public final class King extends Piece {
    private static final String BLACK_NAME = "K";
    private static final String WHITE_NAME = "k";
    private static final double SCORE = 0D;
    private static final String TYPE = "king";

    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovableRange(Position position) {
        return isCorrectDirection(position) && isCorrectDistance(position);
    }

    private boolean isCorrectDirection(Position position) {
        return this.position.isVertical(position) ||
                this.position.isHorizontal(position) ||
                this.position.isDiagonal(position);
    }

    private boolean isCorrectDistance(Position position) {
        return this.position.isOneStepAway(position);
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public List<Position> getIntervalPosition(Piece targetPiece) {
        return Collections.emptyList();
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
