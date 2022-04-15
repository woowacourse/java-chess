package chess.piece;

import chess.chessboard.position.Direction;
import chess.game.Player;
import chess.chessboard.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.chessboard.position.Direction.*;

public final class Rook extends Piece {

    private static final double SCORE = 5;

    public Rook(final Player player, final Symbol symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> board) {
        final List<Position> positions = new ArrayList<>();
        for (Direction direction : getDirections()) {
            positions.addAll(createMovablePositions(direction, source, board));
        }
        return positions.contains(target);
    }

    private List<Position> createMovablePositions(final Direction direction, final Position source, final Map<Position, Piece> board) {
        final List<Position> positions = new ArrayList<>();
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
        return List.of(EAST, WEST, SOUTH, NORTH);
    }

    @Override
    public double addTo(final double score) {
        return score + SCORE;
    }
}
