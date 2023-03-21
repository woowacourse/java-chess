package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> board;

    ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePieceOn(Position fromPosition, Position toPosition) {
        board.put(toPosition, board.get(fromPosition));
        board.put(fromPosition, new Empty());
    }

    public Piece choicePiece(Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
