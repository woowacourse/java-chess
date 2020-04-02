package chess.domain.board;

public interface Location {

    Location opposite();

    Location jump(int index);

    Location next();

    Location previous();

    String getName();
}
