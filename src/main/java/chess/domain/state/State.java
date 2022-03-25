package chess.domain.state;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public interface State {

    State movePiece(Position src, Position dest);

    Map<Position, Piece> getBoard();
}
