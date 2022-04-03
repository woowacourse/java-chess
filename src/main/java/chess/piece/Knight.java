package chess.piece;

import chess.chessBoard.Direction;
import chess.game.Player;
import chess.chessBoard.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.chessBoard.Direction.*;

public class Knight extends Piece {

    private static final double SCORE = 2.5;

    public Knight(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Direction> directions = getDirections();
        List<Position> positions = directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> !board.get(position).isSame(player))
                .collect(Collectors.toUnmodifiableList());
        return positions.contains(target);
    }

    @Override
    protected List<Direction> getDirections() {
        return List.of(KNIGHT_EAST_RIGHT,
                KNIGHT_EAST_LEFT,
                KNIGHT_WEST_RIGHT,
                KNIGHT_WEST_LEFT,
                KNIGHT_SOUTH_RIGHT,
                KNIGHT_SOUTH_LEFT,
                KNIGHT_NORTH_RIGHT,
                KNIGHT_NORTH_LEFT
        );
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
