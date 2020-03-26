package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece extends Square{
    private final Player player;

    protected List<Direction> directions = new ArrayList<>();

    public Piece(Player player, PieceName pieceName) {
        super(pieceName.getName(player));

        Objects.requireNonNull(player);
        this.player = player;
    }

    abstract boolean validateMovableTileSize(Position from, Position to);

    public boolean hasDirection(Direction direction) {
        return directions.contains(direction);
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean movable(Position from, Position to){
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        return hasDirection(Direction.getDirection(from, to))
                && validateMovableTileSize(from, to);
    }

    @Override
    public boolean isSamePlayer(Square target) {
        if (target.getClass() == Empty.class) {
            return false;
        }
        return player == ((Piece)target).getPlayer();
    }
}
