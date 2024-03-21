package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;

public interface Piece {
    Piece move(Position newPosition, boolean isDisturbed, boolean isSameTeamExist);

    PieceType getType();

    PieceInfo getPieceInfo();

    Team getTeam();

    boolean isDifferentTeam(Team otherTeam);
}
