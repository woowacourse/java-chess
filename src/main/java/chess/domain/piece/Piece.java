package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;

public interface Piece {
    boolean move(Position newPosition, Board board, boolean isDisturbed);

    PieceType getType();

    PieceInfo getPieceInfo();
}
