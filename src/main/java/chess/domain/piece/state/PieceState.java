package chess.domain.piece.state;

import java.util.List;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Position;

public interface PieceState {
    List<Position> findMovablePositions(Position source, ChessBoard board);

    PieceState killed();

    PieceState updateState();
}
