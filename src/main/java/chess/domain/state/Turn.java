package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Square;

import javax.xml.transform.Source;
import java.util.Map;

public interface Turn {
    Turn checkMovable(Map<Square, Piece> board, Square source, Square destination, Piece sourcePiece, Piece destinationPiece);
}
