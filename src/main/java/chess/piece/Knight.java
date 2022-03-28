package chess.piece;

import chess.Direction;
import chess.Player;
import chess.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.Direction.*;

public class Knight extends Piece {

    private static final double SCORE = 2.5;

    public Knight(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Direction> directions = getDirection();
        List<Position> positions = directions.stream()
                .filter(source::isInBoardAfterMoved)
                .map(source::createMovablePosition)
                .filter(position -> !board.get(position).isSame(player))
                .collect(Collectors.toUnmodifiableList());
        return positions.contains(target);
    }

    @Override
    protected List<Direction> getDirection() {
        return List.of(EEN, EES, WWN, WWS, SSE, SSW, NNE, NNW);
    }

    @Override
    public double addTo(double score) {
        return score + SCORE;
    }
}
