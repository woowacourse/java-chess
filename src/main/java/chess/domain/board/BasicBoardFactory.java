package chess.domain.board;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class BasicBoardFactory implements BoardFactory{

    private static final int INITIAL_CAPACITY = 64;
    private static final int START_EMPTY_ROW = 6;
    private static final int END_EMPTY_ROW = 3;

    @Override
    public Map<Position, Piece> create() {
        final Map<Position, Piece> board = new HashMap<>(INITIAL_CAPACITY);
        initializePieces(board);
        return board;
    }

    private void initializePieces(final Map<Position, Piece> board) {
        putPiecesWithoutPawn(board, Row.EIGHT, Color.BLACK);
        putPiecesOnRow(board, Row.SEVEN, new PawnPiece(Color.BLACK));

        for (int i = START_EMPTY_ROW; i >= END_EMPTY_ROW; i--) {
            putPiecesOnRow(board, Row.of(i), new EmptyPiece());
        }

        putPiecesOnRow(board, Row.TWO, new PawnPiece(Color.WHITE));
        putPiecesWithoutPawn(board, Row.ONE, Color.WHITE);
    }

    private void putPiecesWithoutPawn(final Map<Position, Piece> board, final Row row, final Color color) {
        board.put(new Position(Column.A, row), new RookPiece(color));
        board.put(new Position(Column.B, row), new KnightPiece(color));
        board.put(new Position(Column.C, row), new BishopPiece(color));
        board.put(new Position(Column.D, row), new QueenPiece(color));
        board.put(new Position(Column.E, row), new KingPiece(color));
        board.put(new Position(Column.F, row), new BishopPiece(color));
        board.put(new Position(Column.G, row), new KnightPiece(color));
        board.put(new Position(Column.H, row), new RookPiece(color));
    }

    private void putPiecesOnRow(final Map<Position, Piece> board, final Row row, final Piece piece) {
        for (Column column : Column.values()) {
            board.put(new Position(column, row), piece);
        }
    }
}
