package chess.piece;

import chess.chessboard.position.Direction;
import chess.game.Player;
import chess.chessboard.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.chessboard.position.Direction.*;

public final class King extends Piece {

    private static final double SCORE = 0;

    public King(final Player player, final Symbol symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Map<Position, Piece> board) {
        final List<Position> positions = getDirections().stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> !board.get(position).isSame(player))
                .collect(Collectors.toUnmodifiableList());
        return positions.contains(target);
    }

    @Override
    protected List<Direction> getDirections() {
        return List.of(EAST, WEST, SOUTH, NORTH, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double addTo(final double score) {
        return score + SCORE;
    }
}
