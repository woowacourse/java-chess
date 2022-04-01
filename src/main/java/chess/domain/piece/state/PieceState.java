package chess.domain.piece.state;

import java.util.List;
import java.util.Map;

import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public interface PieceState {
    List<Position> findMovablePositions(Position source, Map<Position, Piece> board);

    PieceState die();

    PieceState updateState();
}
