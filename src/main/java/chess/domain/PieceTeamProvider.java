package chess.domain;

@FunctionalInterface
public interface PieceTeamProvider {
    Team getTeamAt(ChessXCoordinate x, ChessYCoordinate y);

}
