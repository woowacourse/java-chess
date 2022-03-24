package chess.domain.piece.state;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;

public interface State {
    List<Position> getMovablePositions(Position source, ChessBoard board);

    State killed();

    State updateState();
}
