package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;

public interface Piece {
    Piece move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist);

    boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeam);

    PieceType getType();

    PieceInfo getPieceInfo();

    Team getTeam();

    boolean isSameTeam(Team otherTeam);
}
