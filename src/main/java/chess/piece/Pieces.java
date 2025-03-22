package chess.piece;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import chess.board.Position;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class Pieces {

    private final Color color;

    private final Map<Position, Piece> value;

    public static Pieces generate() {
        Map<Position, Piece> value = new HashMap<>();

        for (InitPieces initPieces : InitPieces.values()) {
            value.put(initPieces.position(), initPieces.piece());
        }

        return new Pieces(Color.WHITE, value);
    }

    public Pieces(final Color color, final Set<Piece> value) {
        this(color, value.stream().collect(toMap(Piece::position, identity())));
    }

    Pieces(final Color color, final Map<Position, Piece> value) {
        this.color = color;
        this.value = new HashMap<>(value);
    }

    public Piece get(final Position position) {
        return value.getOrDefault(position, new Blank(position));
    }

    public Set<Piece> toSet(){
        return new HashSet<>(value.values());
    }

    public void update(Position destination, Piece piece){
        value.put(destination, piece);

    }

    public boolean isSameColor(final Position position) {
        return color.equals(get(position).color());
    }

    public boolean isOpposite(final Position position) {
        return color.opposite().equals(get(position).color());
    }

    public boolean isBlank(final Position position) {
        return get(position).color().isEmpty();
    }

    public Color color() {
        return color;
    }

    public Map<Position, Piece> value() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pieces pieces = (Pieces) o;
        return color == pieces.color && Objects.equals(value, pieces.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }

    @Override
    public String toString() {
        return "Pieces{" +
                "color=" + color +
                ", value=" + value +
                '}';
    }
}
