package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePieceOn(Position fromPosition, Position toPosition) {
        board.put(toPosition, board.get(fromPosition));
        board.put(fromPosition, new Empty());
    }

    public Piece choicePiece(Position position) {
        return board.get(position);
    }

    public List<Piece> choiceBetweenPiece(List<Position> betweenPosition) {
        List<Piece> betweenPiece = new ArrayList<>();
        for (Position position : betweenPosition) {
            betweenPiece.add(board.get(position));
        }
        return betweenPiece;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
