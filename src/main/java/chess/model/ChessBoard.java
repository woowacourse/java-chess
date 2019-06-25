package chess.model;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private Map<Point, AbstractChessPiece> board;

    public ChessBoard(final Map<Point, AbstractChessPiece> board) {
        this.board = board;
    }

    public ChessBoard() {
        board = new HashMap<>();
        initializeOthers(1, ChessPieceColor.WHITE);
        initializeOthers(8, ChessPieceColor.BLACK);
        initializePawns(2, ChessPieceColor.WHITE);
        initializePawns(7, ChessPieceColor.BLACK);
    }

    private void initializePawns(int y, ChessPieceColor color) {
        for (int x = 1; x <= 8 ; x++) {
            board.put(new Point(x, y), AbstractPawn.getInstance(color));
        }
    }

    private void initializeOthers(int y, ChessPieceColor color) {
        board.put(new Point(1, y), new Rook(color));
        board.put(new Point(2, y), new Knight(color));
        board.put(new Point(3, y), new Bishop(color));
        board.put(new Point(4, y), new King(color));
        board.put(new Point(5, y), new Queen(color));
        board.put(new Point(6, y), new Bishop(color));
        board.put(new Point(7, y), new Knight(color));
        board.put(new Point(8, y), new Rook(color));
    }

    public boolean isEmpty(final Point point) {
        return board.get(point) == null;
    }

    public boolean isSameColor(final Point point, final ChessPieceColor color) {
        return board.get(point).isSameColor(color);
    }

    public boolean canMove(final Point source, final Point target) {
        return board.get(source).canMove(source, target, (Point p) -> board.get(p));
    }

    public void move(final Point source, final Point target) {
        board.put(target, board.get(source));
        board.remove(source);
    }

    public boolean isSameType(final Point point, final ChessPieceType type) {
        return board.get(point).isType(type);
    }

    public ChessBoard getWhite() {
        Map<Point, AbstractChessPiece> whitePieces = new HashMap<>();
        for (Map.Entry<Point, AbstractChessPiece> entry : board.entrySet()) {
            if (isSameColor(entry.getKey(), ChessPieceColor.WHITE)) {
                whitePieces.put(entry.getKey(), entry.getValue());
            }
        }
        return new ChessBoard(whitePieces);
    }

    public ChessBoard getBlack() {
        Map<Point, AbstractChessPiece> blackPieces = new HashMap<>();
        for (Map.Entry<Point, AbstractChessPiece> entry : board.entrySet()) {
            if (isSameColor(entry.getKey(), ChessPieceColor.BLACK)) {
                blackPieces.put(entry.getKey(), entry.getValue());
            }
        }
        return new ChessBoard(blackPieces);
    }
}
