package chess.piece;

import chess.chessBoard.Direction;
import chess.game.Player;
import chess.chessBoard.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static chess.chessBoard.Direction.*;

public class Bishop extends Piece {

    private static final double SCORE = 3;
    public Bishop(Player player, String symbol) {
        super(player, symbol);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : getDirections()) {
            positions.addAll(createMovablePositions(direction, source, board));
        }
        return positions.contains(target);
    }

    private List<Position> createMovablePositions(Direction direction, Position source, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
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
