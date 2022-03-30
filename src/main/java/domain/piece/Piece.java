package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    private final Player player;
    private final PieceSymbol pieceSymbol;
    private final Map<Direction, List<Position>> availableMovePosition;

    public Piece(final Player player, final PieceSymbol pieceSymbol) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
        this.availableMovePosition = new HashMap<>();
    }

    protected abstract List<Position> calculateAvailablePosition(final Position source,
        final Direction direction);

    protected abstract List<Direction> getDirections();

    public void generateAvailablePosition(final Position source) {
        getDirections().forEach(direction ->
            availableMovePosition.put(direction, calculateAvailablePosition(source, direction)));
    }

    public List<Position> getAvailablePositions(final Position source, final Position target) {
        List<Position> positions = availableMovePosition.get(findDirection(target));
        int index = positions.indexOf(target);
        return positions.subList(0, index);
    }

    public Direction findDirection(final Position target) {
        return availableMovePosition.keySet().stream()
            .filter(direction -> availableMovePosition.get(direction).contains(target))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 목적지입니다."));
    }

    protected boolean checkOverRange(final Position source, final Direction direction) {
        int rank = source.getRank() + direction.getRank();
        int file = source.getFile() + direction.getFile();
        return File.isFileRange(file) && Rank.isRankRange(rank);
    }

    protected Position createPositionByDirection(final Position source, final Direction direction) {
        int rank = source.getRank() + direction.getRank();
        int file = source.getFile() + direction.getFile();
        return Position.of(File.of(file), Rank.of(rank));
    }

    protected Map<Direction, List<Position>> getAvailableMovePosition() {
        return Map.copyOf(availableMovePosition);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isBlank() {
        return false;
    }

    public boolean isSamePlayer(Player player) {
        return this.player == player;
    }

    public Player getPlayer() {
        return player;
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
}
