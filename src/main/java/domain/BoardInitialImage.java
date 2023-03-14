package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardInitialImage {

    private static List<List<Piece>> boardImage;

    static {
        boardImage = makeBoardImage();
    }

    private static List<List<Piece>> makeBoardImage() {
        List<List<Piece>> boardImage = new ArrayList<>();
        boardImage.add(makeFrontRank(Camp.WHITE));
        boardImage.add(makeBackRank(Camp.WHITE));
        boardImage.addAll(makeEmptyRanks());
        boardImage.add(makeBackRank(Camp.BLACK));
        boardImage.add(makeFrontRank(Camp.BLACK));
        return boardImage;
    }

    private static List<List<Piece>> makeEmptyRanks() {
        return IntStream.range(0, 4)
                .mapToObj(i -> makeEmptyRank())
                .collect(Collectors.toList());
    }

    private static List<Piece> makeFrontRank(Camp camp) {
        List<PieceType> frontPieceTypes = List.of(
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP,
                PieceType.QUEEN, PieceType.KING, PieceType.BISHOP,
                PieceType.KNIGHT, PieceType.ROOK
        );

        return frontPieceTypes.stream()
                .map(pieceType -> new ConcretePiece(pieceType, camp))
                .collect(Collectors.toList());
    }

    private static List<Piece> makeEmptyRank() {
        return IntStream.range(0, 8)
                .mapToObj(i -> new EmptyPiece())
                .collect(Collectors.toList());
    }

    private static List<Piece> makeBackRank(Camp camp) {
        return IntStream.range(0, 8)
                .mapToObj(i -> new ConcretePiece(PieceType.PAWN, camp))
                .collect(Collectors.toList());
    }

    public static Piece getPieceByCoordinate(int row, int col) {
        return boardImage.get(row)
                .get(col);
    }
}
