package domain.board;

import domain.piece.Piece;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.pawn.BlackInitPawn;
import domain.piece.pawn.WhiteInitPawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import domain.square.Color;
import domain.square.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BoardInitialImage {

    public static final int RANK_SIZE = 8;
    public static final int EMPTY_RANK_SIZE = 4;

    private static final List<List<Square>> boardImage;

    static {
        boardImage = makeBoardImage();
    }

    private static List<List<Square>> makeBoardImage() {
        List<List<Square>> boardImage = new ArrayList<>();
        boardImage.add(makeFrontRank(Color.WHITE));
        boardImage.add(makeWhiteBackRank());
        boardImage.addAll(makeEmptyRanks());
        boardImage.add(makeBlackBackRank());
        boardImage.add(makeFrontRank(Color.BLACK));
        return boardImage;
    }

    private static List<List<Square>> makeEmptyRanks() {
        return IntStream.range(0, EMPTY_RANK_SIZE)
                .mapToObj(i -> makeEmptyRank())
                .collect(Collectors.toList());
    }

    private static List<Square> makeFrontRank(final Color color) {
        List<Piece> frontPieces = List.of(
                new Rook(), new Knight(), new Bishop(),
                new Queen(), new King(), new Bishop(),
                new Knight(), new Rook()
        );

        return frontPieces.stream()
                .map(pieceType -> new Square(pieceType, color))
                .collect(Collectors.toList());
    }

    private static List<Square> makeEmptyRank() {
        return IntStream.range(0, RANK_SIZE)
                .mapToObj(i -> Square.ofEmptyPiece())
                .collect(Collectors.toList());
    }

    private static List<Square> makeWhiteBackRank() {
        return IntStream.range(0, RANK_SIZE)
                .mapToObj(i -> new Square(new WhiteInitPawn(), Color.WHITE))
                .collect(Collectors.toList());
    }

    private static List<Square> makeBlackBackRank() {
        return IntStream.range(0, RANK_SIZE)
                .mapToObj(i -> new Square(new BlackInitPawn(), Color.BLACK))
                .collect(Collectors.toList());
    }

    public static Square getSquareByCoordinate(final int row, final int col) {
        return boardImage.get(row)
                .get(col);
    }
}
