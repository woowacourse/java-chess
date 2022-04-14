package domain.piece;

import domain.Navigation;
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
        Direction direction = direction(source, target);
        List<Position> positions = new Navigation(source, target).route(direction);
        return positions.subList(SUBLIST_START_INDEX, positions.indexOf(target));
    }

    protected Direction direction(Position source, Position target) {
        int file = target.getFile() - source.getFile();
        int rank = target.getRank() - source.getRank();
        Direction direction = Direction.calculateDirection(file, rank);
        if (!getDirections().contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 기물이 이동할 수 없는 위치입니다.");
        }
        return direction;
    }

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

    public String symbol() {
        return pieceSymbol.symbol();
    }

    public String symbolByPlayer() {
        return pieceSymbol.symbol(player);
    }
}
