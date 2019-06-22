package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

import static chess.domain.Team.*;

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

    public void move(Position source, Position target, AbstractPiece sourcePiece) {
        board.remove(source);
        board.put(target, sourcePiece);
    }

    public Map<Position, AbstractPiece> getBoard() {
        return board;
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
