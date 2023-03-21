package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> board;

    ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePieceOn(Position fromPosition, Position toPosition) {
        board.put(toPosition, board.get(fromPosition));
        board.remove(fromPosition);
    }

    public Piece choicePiece(Position position) {
        return board.get(position);
    }

    public List<Boolean> choiceBetweenPiece(List<Position> betweenPosition) {
        List<Boolean> betweenPiece = new ArrayList<>();
        for (Position position : betweenPosition) {
            betweenPiece.add(isPieceExist(position));
        }
        return betweenPiece;
    }

    public boolean isPieceExist(Position position) {
        return board.containsKey(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
