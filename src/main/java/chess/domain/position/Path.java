package chess.domain.position;

import chess.domain.ChessBoard;
import java.util.Objects;

public class Path {

    private final Position source;
    private final Position destination;

    public Path(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    public Path(String source, String destination) {
        this.source = Position.from(source);
        this.destination = Position.from(destination);
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Path path = (Path) o;
        return Objects.equals(source, path.source) && Objects.equals(destination,
            path.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }
}
