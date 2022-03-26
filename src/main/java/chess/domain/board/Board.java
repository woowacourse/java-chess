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
    private Team turn = Team.WHITE;

    public void initBoard() {
        initWhitePieces();
        initBlackPieces();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void move(Position from, Position to) {
        Piece piece = board.get(from);

        validNowTurn(piece);
        piece.movable(from, to);
        validArrive(piece, board.get(to));
        validPath(from, to, piece.findDirection(from, to));

        board.put(to, piece);
        board.remove(from);
        turn = turn.change();
    }

    private void validNowTurn(Piece piece) {
        if (!piece.isSameTeam(turn)) {
            throw new IllegalArgumentException("현재 차례는 " + turn + "입니다.");
        }
    }

    private void validArrive(Piece from, Piece to) {
        if (Objects.nonNull(to) && from.isSameTeam(to)) {
            throw new IllegalArgumentException("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
        }
    }

    private void validPath(Position from, Position to, Direction direction) {
        Position current = from;
        do {
            current = current.move(direction);
            if (board.get(current) != null) {
                throw new IllegalArgumentException("이동 경로에 말이 있습니다.");
            }
        } while (!current.equals(to));
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
