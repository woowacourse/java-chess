package chess.model;

public interface Color {

    boolean isWhite();

    boolean isDifferent(Color color);

    boolean isNotEmpty();

    boolean isEmpty();
}
