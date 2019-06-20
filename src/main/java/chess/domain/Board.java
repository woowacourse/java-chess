package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.Team.*;

public class Board {
    private final Map<Position, AbstractPiece> board;

    private Board() {
        board = new HashMap<>();
        List<Character> columnName = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');

        for (int i = 0; i < columnName.size(); i++) {
            board.put(new Position(new Coordinate(columnName.get(i)), new Coordinate(8)), chessPieces(BLACK).get(i));
            board.put(new Position(new Coordinate(columnName.get(i)), new Coordinate(1)), chessPieces(WHITE).get(i));
        }

        columnName.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(7)), new Pawn(BLACK)));
        columnName.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(2)), new Pawn(WHITE)));
    }

    private List<AbstractPiece> chessPieces(final Team team) {
        return Arrays.asList(
                new Rook(team), new Knight(team), new Bishop(team),
                new Queen(team), new King(team), new Bishop(team),
                new Knight(team), new Rook(team)
        );
    }

    private static Board instance;

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }

        return instance;
    }

    public AbstractPiece at(final Position position) {
        return board.get(position);
    }

// FIXME: OutputView 관련
//    public String boardAt(final Position position) {
//        if (!board.containsKey(position)) {
//            return ".";
//        }
//
//        AbstractPiece abstractPiece = board.get(position);
//
//        if (abstractPiece.isBlackTeam()) {
//            return abstractPiece.getName();
//        }
//        return abstractPiece.getName().toUpperCase();
//    }

    public void move(Position source, Position target, AbstractPiece sourceAbstractPiece) {
        board.remove(source);
        board.put(target, sourceAbstractPiece);
    }
}
