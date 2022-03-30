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

public class BoardInitializer {

    public static Map<Position, Piece> initialize() {
        Map<Position, Piece> board = initEmptyBoard();

        initFirstLine(Color.BLACK, Row.EIGHT, board);
        initPawn(Color.BLACK, Row.SEVEN, board);
        initFirstLine(Color.WHITE, Row.ONE, board);
        initPawn(Color.WHITE, Row.TWO, board);
        return board;
    }

    private static Map<Position, Piece> initEmptyBoard() {
        Map<Position, Piece> emptyBoard = new TreeMap<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                emptyBoard.put(Position.valueOf(column.getName() + row.getValue()),
                        new Blank());
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
        Column[] columns = Column.values();
        for (int i = 0; i < columns.length; i++) {
            board.replace(Position.valueOf(columns[i], row), pieces.get(i));
        }
    }

    private static void initPawn(final Color color, Row row, final Map<Position, Piece> board) {
        Column[] columns = Column.values();
        for (int i = 0; i < columns.length; i++) {
            board.replace(Position.valueOf(columns[i], row), new Pawn(color));
        }
    }

}
