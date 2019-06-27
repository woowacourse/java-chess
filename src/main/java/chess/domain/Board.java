package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class Board {
    private final Map<Position, AbstractPiece> board;

    public Board(Map<Position, AbstractPiece> board) {
        this.board = new HashMap<>(board);
    }

    public static Board init() {
        return new Board(initialBoard());
    }

    private static Map<Position, AbstractPiece> initialBoard() {
        Map<Position, AbstractPiece> init = new HashMap<>();
        IntStream.rangeClosed(1, 8)
                .forEach(i -> {
                    init.put(new Position(new Coordinate(i), new Coordinate(8)), chessPieces(BLACK).get(i - 1));
                    init.put(new Position(new Coordinate(i), new Coordinate(1)), chessPieces(WHITE).get(i - 1));
                    init.put(new Position(new Coordinate(i), new Coordinate(7)), new Pawn(BLACK));
                    init.put(new Position(new Coordinate(i), new Coordinate(2)), new Pawn(WHITE));
                });
        return init;
    }

    private static List<AbstractPiece> chessPieces(final Team team) {
        return Arrays.asList(
                new Rook(team), new Knight(team), new Bishop(team),
                new Queen(team), new King(team), new Bishop(team),
                new Knight(team), new Rook(team)
        );
    }

    public AbstractPiece at(final Position position) {
        return board.get(position);
    }

    public Board move(Position source, Position target, AbstractPiece sourcePiece) {
        Board board = new Board(this.board);
        board.remove(source);
        board.put(target, sourcePiece);
        return board;
    }

    private void remove(Position position) {
        board.remove(position);
    }

    private void put(Position position, AbstractPiece abstractPiece) {
        board.put(position, abstractPiece);
    }

    public Map<Position, AbstractPiece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Set<Position> keySet() {
        return board.keySet();
    }

    public AbstractPiece get(Position key) {
        return board.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Objects.equals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }
}
