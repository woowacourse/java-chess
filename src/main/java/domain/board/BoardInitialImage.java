package domain.board;

import domain.piece.Camp;
import domain.piece.ConcretePiece;
import domain.piece.EmptyPiece;
import domain.piece.Piece;
import domain.piecetype.Bishop;
import domain.piecetype.BlackPawn;
import domain.piecetype.King;
import domain.piecetype.Knight;
import domain.piecetype.PieceType;
import domain.piecetype.Queen;
import domain.piecetype.Rook;
import domain.piecetype.WhitePawn;

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
        boardImage.add(makeWhiteBackRank());
        boardImage.addAll(makeEmptyRanks());
        boardImage.add(makeBlackBackRank());
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
                new Rook(), new Knight(), new Bishop(),
                new Queen(), new King(), new Bishop(),
                new Knight(), new Rook()
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

    private static List<Piece> makeWhiteBackRank() {
        return IntStream.range(0, 8)
                .mapToObj(i -> new ConcretePiece(new WhitePawn(), Camp.WHITE))
                .collect(Collectors.toList());
    }

    private static List<Piece> makeBlackBackRank() {
        return IntStream.range(0, 8)
                .mapToObj(i -> new ConcretePiece(new BlackPawn(), Camp.BLACK))
                .collect(Collectors.toList());
    }

    public static Piece getPieceByCoordinate(int row, int col) {
        return boardImage.get(row)
                .get(col);
    }
}
