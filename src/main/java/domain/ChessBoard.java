package domain;

import domain.piece.ChessBoardGenerator;
import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.position.Position;
import dto.BoardStatus;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(final ChessBoardGenerator chessBoardGenerator) {
        this(chessBoardGenerator.generate());
    }

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public BoardStatus status() {
        return BoardStatus.from(board);
    }

    public void move(String from, String to) {
        Position source = new Position(from);
        Position target = new Position(to);

        Piece sourcePiece = board.get(source);
        if (isSamePosition(source, target)
                || isMovablePosition(target, sourcePiece.color())
                || !sourcePiece.isInMovableRange(source, target)) {
            throw new IllegalArgumentException("이동이 불가능합니다.");
        }
        board.put(target, sourcePiece);
        board.remove(source);
    }

    private boolean isMovablePosition(Position target, PieceColor color) {
        if (!isExist(target)) {
            return false;
        }
        return isSameColor(target, color);
    }

    private static boolean isSamePosition(Position source, Position target) {
        return source.equals(target);
    }

    private boolean isSameColor(Position target, PieceColor color) {
        return board.get(target).isColor(color);
    }

    private boolean isExist(Position target) {
        return board.containsKey(target);
    }
}
