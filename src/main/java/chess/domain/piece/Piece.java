package chess.domain.piece;

import chess.domain.pieceInfo.PieceInfo;
import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.Team;

import java.util.List;

public interface Piece {
    Piece move(final Position newPosition, final boolean isDisturbed,
               final boolean isOtherPieceExist, final boolean isSameTeamExist);

    PieceType getType();

    PieceInfo getPieceInfo();

    Team getTeam();

    Position getPosition();

    boolean isSameTeam(Team otherTeam);

    boolean isSamePieceWithSameTeam(List<Piece> pieces);
}
