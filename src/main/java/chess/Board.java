package chess;

import chess.piece.Bishop;
import chess.piece.Blank;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = new TreeMap<>(board);
    }

    public static Board create() {
        List<Position> positions = Position.init();
        Map<Position, Piece> emptyBoard = initEmptyBoard(positions);

        initFirstLine(Color.BLACK, Row.EIGHT, emptyBoard);
        initPawn(Color.BLACK, Row.SEVEN, emptyBoard);
        initFirstLine(Color.WHITE, Row.ONE, emptyBoard);
        initPawn(Color.WHITE, Row.TWO, emptyBoard);
        return new Board(emptyBoard);
    }

    private static Map<Position, Piece> initEmptyBoard(final List<Position> positions) {
        Map<Position, Piece> value = new TreeMap<>();
        for (Position position : positions) {
            value.put(position, new Blank(Color.NONE));
        }
        return value;
    }

    private static void initFirstLine(final Color color, Row row, final Map<Position, Piece> board) {
        List<Piece> pieces = List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new King(color),
                new Queen(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
                );
        Column[] columns = Column.values();
        for (int i = 0; i < 8; i++) {
            board.replace(new Position(columns[i], row), pieces.get(i));
        }
    }

    private static void initPawn(final Color color, Row row, final Map<Position, Piece> board) {
        Column[] columns = Column.values();
        for (int i = 0; i < 8; i++) {
            board.replace(new Position(columns[i], row), new Pawn(color));
        }
    }

    public Piece getPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
