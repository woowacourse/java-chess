package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public interface Piece {
    Piece move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist);

    PieceType getType();

    PieceInfo getPieceInfo();

    Team getTeam();

    Position getPosition();

    boolean isSameTeam(Team otherTeam);

    boolean isSamePieceWithSameTeam(List<Piece> pieces);
}
