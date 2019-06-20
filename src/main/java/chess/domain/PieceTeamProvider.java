package chess.domain;

@FunctionalInterface
public interface PieceTeamProvider {
    Team getTeamAt(ChessCoordinate coord);

}
