package chess.domain;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, AbstractPiece> board;

    private Board() {
        board = new HashMap<>();
        List<Character> aToH = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
        List<AbstractPiece> piecesTeamBlack = Arrays.asList(
                new Rook(Team.BLACK), new Knight(Team.BLACK), new Bishop(Team.BLACK),
                new Queen(Team.BLACK), new King(Team.BLACK), new Bishop(Team.BLACK),
                new Knight(Team.BLACK), new Rook(Team.BLACK)
        );
        List<AbstractPiece> piecesTeamWhite = Arrays.asList(
                new Rook(Team.WHITE), new Knight(Team.WHITE), new Bishop(Team.WHITE),
                new Queen(Team.WHITE), new King(Team.WHITE), new Bishop(Team.WHITE),
                new Knight(Team.WHITE), new Rook(Team.WHITE)
        );

        for (int i = 0; i < aToH.size(); i++) {
            board.put(new Position(new Coordinate(aToH.get(i)), new Coordinate(8)), piecesTeamBlack.get(i));
            board.put(new Position(new Coordinate(aToH.get(i)), new Coordinate(1)), piecesTeamWhite.get(i));
        }

        aToH.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(7)), new Pawn(Team.BLACK)));
        aToH.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(2)), new Pawn(Team.WHITE)));
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

    public String boardAt(final Position position) {
        if (!board.containsKey(position)) {
            return ".";
        }

        AbstractPiece abstractPiece = board.get(position);

        if (abstractPiece.isBlackTeam()) {
            return abstractPiece.getName();
        }
        return abstractPiece.getName().toUpperCase();
    }

    public void move(Position source, Position target, AbstractPiece sourceAbstractPiece) {
        board.remove(source);
        board.put(target, sourceAbstractPiece);
    }
}
