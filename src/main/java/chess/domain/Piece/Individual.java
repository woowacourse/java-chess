package chess.domain.Piece;

import chess.domain.Piece.team.Team;

public interface Individual {
    Team getTeam();
    boolean isNotBlank();
    boolean isBlank();
}
