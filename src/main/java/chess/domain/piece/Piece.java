package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;

public interface Piece {
    boolean move(Position newPosition, Board board);

    PieceType getType();

    PieceInfo getPieceInfo();
}
