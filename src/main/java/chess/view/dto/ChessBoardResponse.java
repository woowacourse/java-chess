package chess.view.dto;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class ChessBoardResponse {

    private final Map<Position, Piece> squares;

    public ChessBoardResponse(final Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public Map<Position, Piece> getSquares() {
        return squares;
    }
}
