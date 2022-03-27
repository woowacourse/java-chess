package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Piece {

    private final Player player;
    private final PieceSymbol pieceSymbol;
    private final Map<Direction, List<Position>> availableMovePosition;

    public Piece(final Player player, final PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
        this.availableMovePosition = initAvailablePosition(directionsGenerator.generate());
    }

    protected abstract List<Position> calculateAvailablePosition(final Position source,
        final Direction direction);

    private Map<Direction, List<Position>> initAvailablePosition(final List<Direction> directions) {
        Map<Direction, List<Position>> availableMovePosition = new HashMap<>();
        directions.forEach(direction -> availableMovePosition.put(direction, null));
        return availableMovePosition;
    }

    public boolean isAvailableMove(final Position source, final Position target) {
        generateAvailablePosition(source);
        return availableMovePosition.values().stream()
            .filter(Objects::nonNull)
            .filter(value -> value.contains(target))
            .findFirst()
            .orElse(null) != null;
    }

    private void generateAvailablePosition(Position source) {
        for (Direction direction : availableMovePosition.keySet()) {
            availableMovePosition.put(direction, calculateAvailablePosition(source, direction));
        }
    }

    public List<Position> getAvailablePositions(final Position target) {
        return availableMovePosition.values().stream()
            .filter(Objects::nonNull)
            .filter(value -> value.contains(target))
            .findFirst()
            .orElse(new ArrayList<>());
    }

    public Direction getDirection(Position target) {
        return availableMovePosition.keySet().stream()
            .filter(direction -> availableMovePosition.get(direction) != null)
            .filter(direction -> availableMovePosition.get(direction).contains(target))
            .findFirst()
            .orElse(null);
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
