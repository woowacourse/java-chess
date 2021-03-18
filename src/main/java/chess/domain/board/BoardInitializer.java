package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardInitializer {

    public static Board initiateBoard() {
        List<List<Square>> lines = Arrays.asList(
                getPiecesOfFirstLine(Horizontal.EIGHT, Owner.BLACK),
                getPiecesOfSecondLine(Horizontal.SEVEN, Owner.BLACK),
                getEmptySquares(Horizontal.SIX),
                getEmptySquares(Horizontal.FIVE),
                getEmptySquares(Horizontal.FOUR),
                getEmptySquares(Horizontal.THREE),
                getPiecesOfSecondLine(Horizontal.TWO, Owner.WHITE),
                getPiecesOfFirstLine(Horizontal.ONE, Owner.WHITE)
        );

        Map<Position, Square> map = new LinkedHashMap<>();
        lines.stream()
                .flatMap(squares -> squares.stream())
                .forEach(square -> map.put(square.getPosition(), square));

        return new Board(map);
    }

    public static List<Square> getPiecesOfFirstLine(Horizontal horizontal, Owner owner){
        List<Piece> pieces = getPieceOrder(owner);
        Vertical[] verticals = Vertical.values();

        return IntStream.range(0, verticals.length)
                .mapToObj(i -> new Square(new Position(verticals[i], horizontal), pieces.get(i)))
                .collect(Collectors.toList());
    }

    public static List<Square> getPiecesOfSecondLine(Horizontal horizontal, Owner owner){
        return Arrays.stream(Vertical.values())
                .map(vertical -> new Square(new Position(vertical, horizontal), Pawn.getInstanceOf(owner)))
                .collect(Collectors.toList());
    }

    public static List<Square> getEmptySquares(Horizontal horizontal){
        return Arrays.stream(Vertical.values())
                .map(vertical -> new Square(new Position(vertical, horizontal)))
                .collect(Collectors.toList());
    }

    private static List<Piece> getPieceOrder(Owner owner) {
        return Arrays.asList(
                new Rook(owner),
                new Knight(owner),
                new Bishop(owner),
                new Queen(owner),
                new King(owner),
                new Bishop(owner),
                new Knight(owner),
                new Rook(owner)
        );
    }
}
