package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    static Map<Position, Piece> createBoard() {
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
                                                       PositionFactory.of(xPoint.getName() + yPoint.getName()));
                board.put(PositionFactory.of(xPoint.getValue(), yPoint.getValue()), emptyPiece);
            }
        }
        return board;
    }

    private static void setBlackPieces(Map<Position, Piece> cells) {
        cells.put(PositionFactory.of("a8"), new Rook(PieceColor.BLACK, PositionFactory.of("a8")));
        cells.put(PositionFactory.of("b8"), new Knight(PieceColor.BLACK, PositionFactory.of("b8")));
        cells.put(PositionFactory.of("c8"), new Bishop(PieceColor.BLACK, PositionFactory.of("c8")));
        cells.put(PositionFactory.of("d8"), new Queen(PieceColor.BLACK, PositionFactory.of("d8")));
        cells.put(PositionFactory.of("e8"), new King(PieceColor.BLACK, PositionFactory.of("e8")));
        cells.put(PositionFactory.of("f8"), new Bishop(PieceColor.BLACK, PositionFactory.of("f8")));
        cells.put(PositionFactory.of("g8"), new Knight(PieceColor.BLACK, PositionFactory.of("g8")));
        cells.put(PositionFactory.of("h8"), new Rook(PieceColor.BLACK, PositionFactory.of("h8")));

        for (char xPoint = 'a'; xPoint <= 'h'; xPoint++) {
            cells.put(PositionFactory.of(xPoint, '7'), new BlackPawn(PositionFactory.of(xPoint, '7')));
        }
    }

    private static void setWhitePieces(Map<Position, Piece> cells) {
        for (char xPoint = 'a'; xPoint <= 'h'; xPoint++) {
            cells.put(PositionFactory.of(xPoint, '2'), new WhitePawn(PositionFactory.of(xPoint, '2')));
        }
        cells.put(PositionFactory.of("a1"), new Rook(PieceColor.WHITE, PositionFactory.of("a1")));
        cells.put(PositionFactory.of("b1"), new Knight(PieceColor.WHITE, PositionFactory.of("b1")));
        cells.put(PositionFactory.of("c1"), new Bishop(PieceColor.WHITE, PositionFactory.of("c1")));
        cells.put(PositionFactory.of("d1"), new Queen(PieceColor.WHITE, PositionFactory.of("d1")));
        cells.put(PositionFactory.of("e1"), new King(PieceColor.WHITE, PositionFactory.of("e1")));
        cells.put(PositionFactory.of("f1"), new Bishop(PieceColor.WHITE, PositionFactory.of("f1")));
        cells.put(PositionFactory.of("g1"), new Knight(PieceColor.WHITE, PositionFactory.of("g1")));
        cells.put(PositionFactory.of("h1"), new Rook(PieceColor.WHITE, PositionFactory.of("h1")));
    }
}
