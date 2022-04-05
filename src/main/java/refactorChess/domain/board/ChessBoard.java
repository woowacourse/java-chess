package refactorChess.domain.board;

import java.util.Map;
import refactorChess.domain.piece.Blank;
import refactorChess.domain.piece.MovePath;
import refactorChess.domain.piece.Piece;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> pieces) {
        this.board = pieces;
    }

    public static ChessBoard create() {
        return new ChessBoard(ChessBoardFactory.initChessBoard());
    }

    public void move(Position from, Position to) {
        final Piece sourcePiece = findByPiece(from);
        final Piece targetPiece = findByPiece(to);

        MovePath movePath = sourcePiece.findByMovePath(targetPiece);
        validatePath(movePath);

        sourcePiece.move(to);
        updateBoard(from, to);
    }

    public Piece findByPiece(Position position) {
        if (board.containsKey(position)) {
            return board.get(position);
        }

        throw new IllegalArgumentException("해당 위치에 아무 값도 없습니다.");
    }

    private void validatePath(MovePath movePath) {
        if (movePath.hasNext()) {
            validateBlank(movePath.next());
        }
    }

    private void validateBlank(Position position) {
        Piece piece = findByPiece(position);

        if (!piece.isBlank()) {
            throw new IllegalArgumentException("이동 경로에 장애물이 존재하여 이동할 수 없습니다.");
        }
    }

    private void updateBoard(Position from, Position to) {
        Piece piece = findByPiece(from);
        board.put(to, piece);
        board.put(from, new Blank(from));
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
