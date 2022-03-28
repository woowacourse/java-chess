package chess.domain.board.generator;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class BasicBoardGenerator implements BoardGenerator {
    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        initBlackPieces();
        initWhitePieces();
        return Map.copyOf(board);
    }

    private void initBlackPieces() {
        board.put(Position.of(Column.A, Row.EIGHT), new Rook(Team.BLACK));
        board.put(Position.of(Column.B, Row.EIGHT), new Knight(Team.BLACK));
        board.put(Position.of(Column.C, Row.EIGHT), new Bishop(Team.BLACK));
        board.put(Position.of(Column.D, Row.EIGHT), new Queen(Team.BLACK));
        board.put(Position.of(Column.E, Row.EIGHT), new King(Team.BLACK));
        board.put(Position.of(Column.F, Row.EIGHT), new Bishop(Team.BLACK));
        board.put(Position.of(Column.G, Row.EIGHT), new Knight(Team.BLACK));
        board.put(Position.of(Column.H, Row.EIGHT), new Rook(Team.BLACK));

        initOneLine(Row.SEVEN, new Pawn(Team.BLACK));
    }

    private void initWhitePieces() {
        board.put(Position.of(Column.A, Row.ONE), new Rook(Team.WHITE));
        board.put(Position.of(Column.B, Row.ONE), new Knight(Team.WHITE));
        board.put(Position.of(Column.C, Row.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(Column.D, Row.ONE), new Queen(Team.WHITE));
        board.put(Position.of(Column.E, Row.ONE), new King(Team.WHITE));
        board.put(Position.of(Column.F, Row.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(Column.G, Row.ONE), new Knight(Team.WHITE));
        board.put(Position.of(Column.H, Row.ONE), new Rook(Team.WHITE));

        initOneLine(Row.TWO, new Pawn(Team.WHITE));
    }

    private void initOneLine(Row row, Piece piece) {
        for (Column column : Column.values()) {
            board.put(Position.of(column, row), piece);
        }
    }
}
