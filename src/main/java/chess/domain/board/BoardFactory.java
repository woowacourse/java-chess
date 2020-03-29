package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static Map<Position, Piece> board = createBoard();

    private static Map<Position, Piece> createBoard() {
        Map<Position, Piece> cells = createEmptyBoard();
        setBlackPieces(cells);
        setWhitePieces(cells);
        return cells;
    }

    private static Map<Position, Piece> createEmptyBoard() {
        Map<Position, Piece> board = new HashMap<>();
        for (Xpoint xPoint : Xpoint.values()) {
            for (Ypoint yPoint : Ypoint.values()) {
                EmptyPiece emptyPiece = new EmptyPiece(PieceColor.NONE,
                                                       Positions.of(xPoint.getName() + yPoint.getName()));
                board.put(Positions.of(xPoint.getValue(), yPoint.getValue()), emptyPiece);
            }
        }
        return board;
    }

    private static void setBlackPieces(Map<Position, Piece> cells) {
        cells.put(Positions.of("a8"), new Rook(PieceColor.BLACK, Positions.of("a8")));
        cells.put(Positions.of("b8"), new Knight(PieceColor.BLACK, Positions.of("b8")));
        cells.put(Positions.of("c8"), new Bishop(PieceColor.BLACK, Positions.of("c8")));
        cells.put(Positions.of("d8"), new Queen(PieceColor.BLACK, Positions.of("d8")));
        cells.put(Positions.of("e8"), new King(PieceColor.BLACK, Positions.of("e8")));
        cells.put(Positions.of("f8"), new Bishop(PieceColor.BLACK, Positions.of("f8")));
        cells.put(Positions.of("g8"), new Knight(PieceColor.BLACK, Positions.of("g8")));
        cells.put(Positions.of("h8"), new Rook(PieceColor.BLACK, Positions.of("h8")));

        for (char xPoint = 'a'; xPoint <= 'h'; xPoint++) {
            cells.put(Positions.of(xPoint, '7'), new Pawn(PieceColor.BLACK, Positions.of(xPoint, '7')));
        }
    }

    private static void setWhitePieces(Map<Position, Piece> cells) {
        for (char xPoint = 'a'; xPoint <= 'h'; xPoint++) {
            cells.put(Positions.of(xPoint, '2'), new Pawn(PieceColor.WHITE, Positions.of(xPoint, '2')));
        }
        cells.put(Positions.of("a1"), new Rook(PieceColor.WHITE, Positions.of("a1")));
        cells.put(Positions.of("b1"), new Knight(PieceColor.WHITE, Positions.of("b1")));
        cells.put(Positions.of("c1"), new Bishop(PieceColor.WHITE, Positions.of("c1")));
        cells.put(Positions.of("d1"), new Queen(PieceColor.WHITE, Positions.of("d1")));
        cells.put(Positions.of("e1"), new King(PieceColor.WHITE, Positions.of("e1")));
        cells.put(Positions.of("f1"), new Bishop(PieceColor.WHITE, Positions.of("f1")));
        cells.put(Positions.of("g1"), new Knight(PieceColor.WHITE, Positions.of("g1")));
        cells.put(Positions.of("h1"), new Rook(PieceColor.WHITE, Positions.of("h1")));
    }

    public static Map<Position, Piece> getBoard() {
        return board;
    }
}
