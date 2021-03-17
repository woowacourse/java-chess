package chess.domain.board;

import chess.domain.board.piece.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardInitializer {

    public static Board initiateBoard() {
        List<Line> lines = Arrays.asList(
            new Line(getPiecesOfFirstLine(Horizontal.ONE, Owner.WHITE)),
            new Line(getPiecesOfSecondLine(Horizontal.TWO, Owner.WHITE)),
            new Line(getEmptySquares(Horizontal.THREE)),
            new Line(getEmptySquares(Horizontal.FOUR)),
            new Line(getEmptySquares(Horizontal.FIVE)),
            new Line(getEmptySquares(Horizontal.SIX)),
            new Line(getPiecesOfSecondLine(Horizontal.SEVEN, Owner.BLACK)),
            new Line(getPiecesOfFirstLine(Horizontal.EIGHT, Owner.BLACK))
        );
        return new Board(lines);
    }

    public static List<Square> getPiecesOfFirstLine(Horizontal horizontal, Owner owner){
        List<Piece> pieces = getPieceOrder(owner);
        Vertical[] verticals = Vertical.values();

        return IntStream.range(0, verticals.length)
                .mapToObj(i -> new Square(verticals[i], horizontal, pieces.get(i)))
                .collect(Collectors.toList());
    }

    public static List<Square> getPiecesOfSecondLine(Horizontal horizontal, Owner owner){
        return Arrays.stream(Vertical.values())
                .map(vertical -> new Square(vertical, horizontal, Pawn.getInstanceOf(owner)))
                .collect(Collectors.toList());
    }

    public static List<Square> getEmptySquares(Horizontal horizontal){
        return Arrays.stream(Vertical.values())
                .map(vertical -> new Square(vertical, horizontal))
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
