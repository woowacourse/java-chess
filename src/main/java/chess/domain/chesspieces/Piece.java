package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece extends Square {
    private final Player player;
    private final PieceInfo pieceInfo;

    protected List<Direction> directions = new ArrayList<>();

    public Piece(Player player, PieceInfo pieceInfo) {
        super(pieceInfo.getName(player));

        Objects.requireNonNull(player);
        this.player = player;
        this.pieceInfo = pieceInfo;
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

    public double getScore() {
        return pieceInfo.getScore();
    }

    @Override
    public boolean movable(Position from, Position to) {
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
        return player == ((Piece) target).getPlayer();
    }

    public PieceInfo getPieceInfo() {
        return pieceInfo;
    }
}
