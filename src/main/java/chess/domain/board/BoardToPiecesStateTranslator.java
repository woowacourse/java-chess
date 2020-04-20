package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;

import java.util.Map;

class BoardToPiecesStateTranslator {
    static PiecesState translate(Board board) {
        Map<Position, Piece> pieces = board.getPieces();
        return new PiecesState(pieces);
    }
}
