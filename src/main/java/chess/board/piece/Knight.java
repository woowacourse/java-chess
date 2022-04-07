package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.Position;

import java.util.Collections;
import java.util.List;

public final class Knight extends Piece {
    private static final String BLACK_NAME = "N";
    private static final String WHITE_NAME = "n";
    private static final double SCORE = 2.5D;
    private static final String TYPE = "knight";

    public Knight(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovableRange(Position position) {
        return this.position.isKnightDirection(position);
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

}
