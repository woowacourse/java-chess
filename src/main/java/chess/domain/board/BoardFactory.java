package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoardFactory {

    public static Board createChessBoard() {
        Map<Position, Piece> emptyBoard = initEmptyBoard();
        initFirstLine(Color.BLACK, Row.EIGHT, emptyBoard);
        initPawn(Color.BLACK, Row.SEVEN, emptyBoard);
        initFirstLine(Color.WHITE, Row.ONE, emptyBoard);
        initPawn(Color.WHITE, Row.TWO, emptyBoard);
        return new Board(emptyBoard);
    }

    private static Map<Position, Piece> initEmptyBoard() {
        Map<Position, Piece> emptyBoard = new TreeMap<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                emptyBoard.put(Position.valueOf(column.getName() + row.getValue()), new Blank());
            }
        }
        return emptyBoard;
    }

    private static void initFirstLine(final Color color, Row row, final Map<Position, Piece> board) {
        List<Piece> pieces = List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
        for (Column column : Column.values()) {
            board.replace(Position.valueOf(column.getName() + row.getValue()), pieces.listIterator().next());
        }
    }

    private static void initPawn(final Color color, Row row, final Map<Position, Piece> board) {
        for (Column column : Column.values()) {
            board.replace(Position.valueOf(column.getName() + row.getValue()), new Pawn(color));
        }
    }
}
