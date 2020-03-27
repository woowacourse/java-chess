package chess.domain.board;

import chess.domain.piece.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board = createBoard();

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> cells = createEmptyCells();
        setBlackPieces(cells);
        setWhitePieces(cells);
        return cells;
    }

    private Map<Position, Piece> createEmptyCells() {
        Map<Position, Piece> cells = new HashMap<>();
        for (Xpoint xPoint : Xpoint.values()) {
            for (Ypoint yPoint : Ypoint.values()) {
                EmptyPiece emptyPiece = new EmptyPiece(TeamColor.NONE, new Position(xPoint, yPoint));
                cells.put(new Position(xPoint, yPoint), emptyPiece);
            }
        }
        return cells;
    }

    private void setBlackPieces(Map<Position, Piece> cells) {
        cells.put(new Position(1, 8), new Rook(TeamColor.BLACK, new Position(1, 8)));
        cells.put(new Position(2, 8), new Knight(TeamColor.BLACK, new Position(2, 8)));
        cells.put(new Position(3, 8), new Bishop(TeamColor.BLACK, new Position(3, 8)));
        cells.put(new Position(4, 8), new Queen(TeamColor.BLACK, new Position(4, 8)));
        cells.put(new Position(5, 8), new King(TeamColor.BLACK, new Position(5, 8)));
        cells.put(new Position(6, 8), new Bishop(TeamColor.BLACK, new Position(6, 8)));
        cells.put(new Position(7, 8), new Knight(TeamColor.BLACK, new Position(7, 8)));
        cells.put(new Position(8, 8), new Rook(TeamColor.BLACK, new Position(8, 8)));

        for (int xPoint = 1; xPoint <= 8; xPoint++) {
            cells.put(new Position(xPoint, 7), new Pawn(TeamColor.BLACK, new Position(xPoint, 7)));
        }
    }

    private void setWhitePieces(Map<Position, Piece> cells) {
        for (int xPoint = 1; xPoint <= 8; xPoint++) {
            cells.put(new Position(xPoint, 2), new Pawn(TeamColor.WHITE, new Position(xPoint, 2)));
        }
        cells.put(new Position(1, 1), new Rook(TeamColor.WHITE, new Position(1, 1)));
        cells.put(new Position(2, 1), new Knight(TeamColor.WHITE, new Position(2, 1)));
        cells.put(new Position(3, 1), new Bishop(TeamColor.WHITE, new Position(3, 1)));
        cells.put(new Position(4, 1), new Queen(TeamColor.WHITE, new Position(4, 1)));
        cells.put(new Position(5, 1), new King(TeamColor.WHITE, new Position(5, 1)));
        cells.put(new Position(6, 1), new Bishop(TeamColor.WHITE, new Position(6, 1)));
        cells.put(new Position(7, 1), new Knight(TeamColor.WHITE, new Position(7, 1)));
        cells.put(new Position(8, 1), new Rook(TeamColor.WHITE, new Position(8, 1)));
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
