package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Direction;
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
import java.util.Objects;

public class Board {
    private final Map<Position, Piece> board = new HashMap<>();

    public void initBoard() {
        initWhitePieces();
        initBlackPieces();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public boolean move(Position from, Position to) {
        Piece piece = board.get(from);
        // TODO - if piece.movable
        if (piece.movable(from, to)
                && validPath(from, to, piece.findDirection(from, to))
                && validArrive(piece, board.get(to))) {
            board.put(to, piece);
            board.remove(from);
            return true;
        }
        return false;
    }

    private boolean validPath(Position from, Position to, Direction direction) {
        Position current = from;
        do {
            current = current.move(direction);
            if (board.get(current) != null) {
                return false;
            }
        } while (!current.equals(to));
        return true;
    }

    private boolean validArrive(Piece from, Piece to) {
        if (Objects.isNull(to)) {
            return true;
        }
        return from.isNotSameTeam(to);
    }

    private void initBlackPieces() {
        board.put(new Position(Column.A, Row.EIGHT), new Rook(Team.BLACK));
        board.put(new Position(Column.B, Row.EIGHT), new Knight(Team.BLACK));
        board.put(new Position(Column.C, Row.EIGHT), new Bishop(Team.BLACK));
        board.put(new Position(Column.D, Row.EIGHT), new Queen(Team.BLACK));
        board.put(new Position(Column.E, Row.EIGHT), new King(Team.BLACK));
        board.put(new Position(Column.F, Row.EIGHT), new Bishop(Team.BLACK));
        board.put(new Position(Column.G, Row.EIGHT), new Knight(Team.BLACK));
        board.put(new Position(Column.H, Row.EIGHT), new Rook(Team.BLACK));

        initOneLine(Row.SEVEN, new Pawn(Team.BLACK));
    }

    private void initWhitePieces() {
        board.put(new Position(Column.A, Row.ONE), new Rook(Team.WHITE));
        board.put(new Position(Column.B, Row.ONE), new Knight(Team.WHITE));
        board.put(new Position(Column.C, Row.ONE), new Bishop(Team.WHITE));
        board.put(new Position(Column.D, Row.ONE), new Queen(Team.WHITE));
        board.put(new Position(Column.E, Row.ONE), new King(Team.WHITE));
        board.put(new Position(Column.F, Row.ONE), new Bishop(Team.WHITE));
        board.put(new Position(Column.G, Row.ONE), new Knight(Team.WHITE));
        board.put(new Position(Column.H, Row.ONE), new Rook(Team.WHITE));

        initOneLine(Row.TWO, new Pawn(Team.WHITE));
    }

    private void initOneLine(Row row, Piece piece) {
        for (Column column : Column.values()) {
            board.put(new Position(column, row), piece);
        }
    }

    public boolean isEmpty() {
        return board.isEmpty();
    }
}
