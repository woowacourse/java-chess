package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;

public interface Piece {
    boolean move(Position newPosition, Board board, boolean isDisturbed);

    PieceType getType();

    PieceInfo getPieceInfo();

    boolean isDifferentTeam(Team otherTeam);
}
