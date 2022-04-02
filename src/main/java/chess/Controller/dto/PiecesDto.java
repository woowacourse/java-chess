package chess.Controller.dto;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class PiecesDto {

    private final Map<Position, Piece> pieces;

    private PiecesDto(final Board board) {
        this.pieces = board.getPieces();
    }

    public static PiecesDto fromEntity(final Board board) {
        return new PiecesDto(board);
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }
}
