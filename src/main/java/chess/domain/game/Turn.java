package chess.domain.game;

import chess.domain.Position;
import chess.domain.TeamColor;

public interface Turn {
    Turn movePiece(Position start, Position target);

    TeamColor getTeamColor();

    boolean isFinished();
}
