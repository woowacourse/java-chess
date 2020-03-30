package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Player player;
    private final PieceInfo pieceInfo;

    protected List<Direction> directions = new ArrayList<>();

    public Piece(Player player, PieceInfo pieceInfo) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(pieceInfo);

        this.player = player;
        this.pieceInfo = pieceInfo;
    }

    public abstract boolean validateTileSize(Position from, Position to);

    public boolean validateDirection(Direction direction, Piece target) {
        return hasDirection(direction);
    };

    protected final boolean hasDirection(Direction direction) {
        return directions.contains(direction);
    }

    public List<Direction> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public Player getPlayer() {
        return player;
    }

    public double getScore() {
        return pieceInfo.getScore();
    }

    public boolean movable(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        return hasDirection(Direction.getDirection(from, to))
                && validateTileSize(from, to);
    }

    public boolean isSamePlayer(Piece target) {
        Objects.requireNonNull(target);
        if (player == Player.NONE || target.getPlayer() == Player.NONE) {
            return false;
        }
        return player == target.getPlayer();
    }

    public String getDisplay() {
        return pieceInfo.getName(player);
    }
}
