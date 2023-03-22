package chessgame.domain.board;

import chessgame.domain.piece.Camp;
import chessgame.domain.piece.ConcretePiece;
import chessgame.domain.piece.EmptyPiece;
import chessgame.domain.piece.Piece;
import chessgame.domain.piecetype.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardInitialImage {

    private static final int BOARD_FILE = 8;
    private static final int WHITE_BACK_RANK = 0;
    private static final int WHITE_FRONT_RANK = 1;
    private static final int START_EMPTY_RANK = 2;
    private static final int END_EMPTY_RANK = 5;
    private static final int BLACK_FRONT_RANK = 6;
    private static final int BLACK_BACK_RANK = 7;

    private static final Map<Coordinate, Piece> boardImage;

    private BoardInitialImage() {
    }

    static {
        boardImage = makeBoardImage();
    }

    private static Map<Coordinate, Piece> makeBoardImage() {
        Map<Coordinate, Piece> boardImage = new HashMap<>();

        addWhiteRanks(boardImage);
        addEmptyRanks(boardImage);
        addBlackRanks(boardImage);
        return boardImage;
    }

    private static void addWhiteRanks(Map<Coordinate, Piece> boardImage) {
        List<Piece> backPieces = makeBackRank(Camp.WHITE);
        List<Piece> frontPieces = makeWhiteFrontRank();

        for (int file = 0; file < BOARD_FILE; file++) {
            boardImage.put(Coordinate.fromOnBoard(WHITE_BACK_RANK, file), backPieces.get(file));
            boardImage.put(Coordinate.fromOnBoard(WHITE_FRONT_RANK, file), frontPieces.get(file));
        }
    }

    private static List<Piece> makeBackRank(Camp camp) {
        List<PieceType> frontPieceTypes = List.of(
                new Rook(), new Knight(), new Bishop(),
                new Queen(), new King(), new Bishop(),
                new Knight(), new Rook()
        );

        return frontPieceTypes.stream()
                              .map(pieceType -> new ConcretePiece(pieceType, camp))
                              .collect(Collectors.toList());
    }

    private static List<Piece> makeWhiteFrontRank() {
        return IntStream.range(0, 8)
                        .mapToObj(i -> new ConcretePiece(new WhitePawn(), Camp.WHITE))
                        .collect(Collectors.toList());
    }

    private static void addEmptyRanks(Map<Coordinate, Piece> boardImage) {
        for (int rank = START_EMPTY_RANK; rank <= END_EMPTY_RANK; rank++) {
            addEachEmptyRank(boardImage, rank);
        }
    }

    private static void addEachEmptyRank(Map<Coordinate, Piece> boardImage, int rank) {
        for (int file = 0; file < BOARD_FILE; file++) {
            boardImage.put(Coordinate.fromOnBoard(rank, file), new EmptyPiece());
        }
    }

    private static void addBlackRanks(Map<Coordinate, Piece> boardImage) {
        List<Piece> backPieces = makeBackRank(Camp.BLACK);
        List<Piece> frontPieces = makeBlackFrontRank();

        for (int file = 0; file < BOARD_FILE; file++) {
            boardImage.put(Coordinate.fromOnBoard(BLACK_FRONT_RANK, file), frontPieces.get(file));
            boardImage.put(Coordinate.fromOnBoard(BLACK_BACK_RANK, file), backPieces.get(file));
        }
    }

    private static List<Piece> makeBlackFrontRank() {
        return IntStream.range(0, 8)
                        .mapToObj(i -> new ConcretePiece(new BlackPawn(), Camp.BLACK))
                        .collect(Collectors.toList());
    }

    public static Map<Coordinate, Piece> generate() {
        return Map.copyOf(boardImage);
    }
}
