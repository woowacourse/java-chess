package chess2.domain2.board2;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.piece2.Piece;
import chess2.domain2.board2.piece2.PieceType;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePiece(Position from, Position to, Color color) {
        validatePieceAndColor(from, color);
        board.put(to, board.remove(from));
    }

    private void validatePieceAndColor(Position from, Color currentColor) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException("해당 위치에 체스 말은 존재하지 않습니다.");
        }
        if (!board.get(from).hasColorOf(currentColor)) {
            throw new IllegalArgumentException(currentColor + " 진영이 움직일 차례입니다!");
        }
    }

    public int countByType(PieceType pieceType) {
        return (int) board.values()
                .stream()
                .filter(piece -> piece.hasTypeOf(pieceType))
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
