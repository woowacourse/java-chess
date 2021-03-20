package chess.domain.piece;

public interface Piece {

    Strategy strategy();

    String getName();

    Team getTeam();

    void validateCurrentTurn(Team team);

    boolean isSameTeam(Team team);

    boolean isPawn();

    boolean isKing();

    double getPoint();
}
