package chess.piece;

import chess.chessboard.position.Direction;
import chess.game.Player;
import chess.chessboard.position.Position;

import java.util.*;

import static chess.chessboard.position.Direction.*;

public final class Bishop extends Piece {

    private static final double SCORE = 3;
    public Bishop(final Player player, final Symbol symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> board) {
        final List<Position> positions = new ArrayList<>();
        for (final Direction direction : getDirections()) {
            positions.addAll(createMovablePositions(direction, source, board));
        }
        return positions.contains(target);
    }

    private Set<Position> createMovablePositions(final Direction direction, final Position source, final Map<Position, Piece> board) {
        Set<Position> positions = new HashSet<>();
        if (!source.isInBoardAfterMoved(direction)) {
            return positions;
        }
        Position nextPosition = source.createMovablePosition(direction);
        while (board.get(nextPosition).isSame(Player.NONE) && nextPosition.isInBoardAfterMoved(direction)) {
            positions.add(nextPosition);
            nextPosition = nextPosition.createMovablePosition(direction);
        }
        if (!board.get(nextPosition).isSame(this.player)) {
            positions.add(nextPosition);
        }
        return positions;
    }

    @Override
    protected List<Direction> getDirections() {
        return List.of(SOUTHEAST, SOUTHWEST, NORTHEAST, NORTHWEST);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
