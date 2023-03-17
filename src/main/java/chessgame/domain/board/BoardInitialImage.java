package chessgame.domain.board;

import chessgame.domain.square.Camp;
import chessgame.domain.square.ConcreteSquare;
import chessgame.domain.square.EmptySquare;
import chessgame.domain.square.Square;
import chessgame.domain.piece.Bishop;
import chessgame.domain.piece.BlackPawn;
import chessgame.domain.piece.King;
import chessgame.domain.piece.Knight;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Queen;
import chessgame.domain.piece.Rook;
import chessgame.domain.piece.WhitePawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardInitialImage {

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
        return IntStream.range(0, 4)
                .mapToObj(i -> makeEmptyRank())
                .collect(Collectors.toList());
    }

    private static List<Square> makeFrontRank(Camp camp) {
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
        return IntStream.range(0, 8)
                .mapToObj(i -> new EmptySquare())
                .collect(Collectors.toList());
    }

    private static List<Square> makeWhiteBackRank() {
        return IntStream.range(0, 8)
                .mapToObj(i -> new ConcreteSquare(new WhitePawn(), Camp.WHITE))
                .collect(Collectors.toList());
    }

    private static List<Square> makeBlackBackRank() {
        return IntStream.range(0, 8)
                .mapToObj(i -> new ConcreteSquare(new BlackPawn(), Camp.BLACK))
                .collect(Collectors.toList());
    }

    public static Square getPieceByCoordinate(int row, int col) {
        return boardImage.get(row)
                .get(col);
    }
}
