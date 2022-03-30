package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private final Player player;
    private final PieceSymbol pieceSymbol;
    private Map<Direction, List<Position>> availableMovePosition;

    public Piece(final Player player, final PieceSymbol pieceSymbol) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
        this.availableMovePosition = new HashMap<>();
    }

    protected abstract List<Direction> getDirections();

    protected abstract List<Position> calculateAvailablePosition(final Position source,
        final Direction direction);

    public boolean isAvailableMove(final Position source, final Position target) {
        generateAvailablePosition(source);
        return availableMovePosition.values().stream()
            .filter(value -> value.contains(target))
            .findFirst()
            .orElse(null) != null;
    }

    protected void generateAvailablePosition(Position source) {
        getDirections().forEach(direction ->
            availableMovePosition.put(direction, calculateAvailablePosition(source, direction)));
    }

    public List<Position> getAvailablePositions(final Position source, final Position target) {
        List<Position> positions = availableMovePosition.values().stream()
            .filter(value -> value.contains(target))
            .findFirst()
            .orElse(new ArrayList<>());
        int index = positions.indexOf(target);
        return positions.subList(0, index);
    }

    public Direction getDirection(Position target) {
        return availableMovePosition.keySet().stream()
            .filter(direction -> availableMovePosition.get(direction).contains(target))
            .findFirst()
            .orElse(null);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isBlank() {
        return false;
    }

    protected boolean checkOverRange(final Position source, final Direction direction) {
        int rank = source.getRank() + direction.getRank();
        int file = source.getFile() + direction.getFile();
        return File.isFileRange(file) && Rank.isRankRange(rank);
    }

    protected Position createDirectionPosition(final Position source, final Direction direction) {
        int rank = source.getRank() + direction.getRank();
        int file = source.getFile() + direction.getFile();
        return Position.of(File.of(file), Rank.of(rank));
    }

    public boolean isSamePlayer(Player player) {
        return this.player == player;
    }

    protected Map<Direction, List<Position>> getAvailableMovePosition() {
        return availableMovePosition;
    }

    public boolean isSamePlayer(Piece comparePiece) {
        return comparePiece.isSamePlayer(player);
    }

    public String symbolByPlayer() {
        return pieceSymbol.symbol(player);
    }

    public String symbol() {
        return pieceSymbol.symbol();
    }

    public Player getPlayer() {
        return player;
    }
}
