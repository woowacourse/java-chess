package chess.domain;

public interface MoveStrategy {
    boolean canMove(Position currentPosition, Position newPosition, Team team, Board board);
}
