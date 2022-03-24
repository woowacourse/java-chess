package chess.domain.state;

import chess.domain.board.Position;

public interface State {

    State movePiece(Position src, Position dest);
}
