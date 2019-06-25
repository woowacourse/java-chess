package chess.domain;

import chess.domain.coordinate.ChessCoordinate;

@FunctionalInterface
public interface PieceTeamProvider {
    Team getTeamAt(ChessCoordinate coord);

}
