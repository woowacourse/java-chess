package chess.domain.piece.state;

import java.util.List;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Position;

public interface PieceState {
    List<Position> getMovablePositions(Position source, ChessBoard board);

    PieceState killed();

    PieceState updateState();
}
