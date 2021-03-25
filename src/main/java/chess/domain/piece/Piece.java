package chess.domain.piece;

public interface Piece {

    DirectionStrategy strategy();

    String getName();

    Team getTeam();

    void validateCurrentTurn(Team team);

    boolean isSameTeam(Team team);

    boolean isPawn();

    boolean isKing();

    double getPoint();
}
