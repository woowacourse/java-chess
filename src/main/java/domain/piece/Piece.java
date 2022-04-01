package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.List;

public abstract class Piece {

    protected static final int SUBLIST_START_INDEX = 0;

    private final Player player;
    private final PieceSymbol pieceSymbol;

    public Piece(final Player player, final PieceSymbol pieceSymbol) {
        this.player = player;
        this.pieceSymbol = pieceSymbol;
    }

    public List<Position> move(Position source, Position target) {
        List<Position> positions = getDirections().stream()
            .map(direction -> calculateAvailablePosition(source, direction))
            .filter(list -> list.contains(target))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 목적지입니다."));
        return positions.subList(SUBLIST_START_INDEX, positions.indexOf(target));
    }

    protected abstract List<Position> calculateAvailablePosition(final Position source,
        final Direction direction);

    protected abstract List<Direction> getDirections();

    public abstract double score(boolean isSeveralPawn);

    public boolean isKing() {
        return false;
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

    public boolean isSamePlayer(final Piece comparePiece) {
        return comparePiece.isSamePlayer(player);
    }

    public String symbolByPlayer() {
        return pieceSymbol.symbol(player);
    }
}
