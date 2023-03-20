package domain.board;

import domain.square.Camp;
import domain.square.ConcreteSquare;
import domain.square.EmptySquare;
import domain.square.Square;
import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;

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
        boardImage.add(makeFrontRank(Camp.WHITE));
        boardImage.add(makeWhiteBackRank());
        boardImage.addAll(makeEmptyRanks());
        boardImage.add(makeBlackBackRank());
        boardImage.add(makeFrontRank(Camp.BLACK));
        return boardImage;
    }

    private static List<List<Square>> makeEmptyRanks() {
        return IntStream.range(0, EMPTY_RANK_SIZE)
                .mapToObj(i -> makeEmptyRank())
                .collect(Collectors.toList());
    }

    private static List<Square> makeFrontRank(final Camp camp) {
        List<Piece> frontPieces = List.of(
                new Rook(), new Knight(), new Bishop(),
                new Queen(), new King(), new Bishop(),
                new Knight(), new Rook()
        );

        return frontPieces.stream()
                .map(pieceType -> new ConcreteSquare(pieceType, camp))
                .collect(Collectors.toList());
    }

    private static List<Square> makeEmptyRank() {
        return IntStream.range(0, RANK_SIZE)
                .mapToObj(i -> new EmptySquare())
                .collect(Collectors.toList());
    }

    private static List<Square> makeWhiteBackRank() {
        return IntStream.range(0, RANK_SIZE)
                .mapToObj(i -> new ConcreteSquare(new WhitePawn(), Camp.WHITE))
                .collect(Collectors.toList());
    }

    private static List<Square> makeBlackBackRank() {
        return IntStream.range(0, RANK_SIZE)
                .mapToObj(i -> new ConcreteSquare(new BlackPawn(), Camp.BLACK))
                .collect(Collectors.toList());
    }

    public static Square getSquareByCoordinate(final int row, final int col) {
        Square findSquare = boardImage.get(row)
                .get(col);

        if (findSquare.isExist()) {
            return new ConcreteSquare((ConcreteSquare)findSquare);
        }
        return new EmptySquare();
    }
}
