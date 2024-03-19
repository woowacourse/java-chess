package chess.domain;

public interface Piece {
    boolean move(Position newPosition, Board board);
}
