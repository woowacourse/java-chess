package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static chess.domain.Team.*;

public class Board {
    private final Map<Position, AbstractPiece> board;

    private Board() {
        board = new HashMap<>();
        IntStream.rangeClosed(1, 8)
                .forEach(i -> {
                    board.put(new Position(new Coordinate(i), new Coordinate(8)), chessPieces(BLACK).get(i - 1));
                    board.put(new Position(new Coordinate(i), new Coordinate(1)), chessPieces(WHITE).get(i - 1));
                    board.put(new Position(new Coordinate(i), new Coordinate(7)), new Pawn(BLACK));
                    board.put(new Position(new Coordinate(i), new Coordinate(2)), new Pawn(WHITE));
                });
    }

    private List<AbstractPiece> chessPieces(final Team team) {
        return Arrays.asList(
                new Rook(team), new Knight(team), new Bishop(team),
                new Queen(team), new King(team), new Bishop(team),
                new Knight(team), new Rook(team)
        );
    }

    public static Board init() {
        return new Board();
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
}
