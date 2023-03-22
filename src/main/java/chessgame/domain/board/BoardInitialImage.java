package chessgame.domain.board;

import chessgame.domain.piece.*;
import chessgame.domain.square.Camp;
import chessgame.domain.square.ConcreteSquare;
import chessgame.domain.square.EmptySquare;
import chessgame.domain.square.Square;

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

    private static final Map<Coordinate, Square> boardImage;

    private BoardInitialImage() {
    }

    static {
        boardImage = makeBoardImage();
    }

    private static Map<Coordinate, Square> makeBoardImage() {
        Map<Coordinate, Square> boardImage = new HashMap<>();

        addWhiteRanks(boardImage);
        addEmptyRanks(boardImage);
        addBlackRanks(boardImage);
        return boardImage;
    }

    private static void addWhiteRanks(Map<Coordinate, Square> boardImage) {
        List<Square> backSquares = makeBackRank(Camp.WHITE);
        List<Square> frontSquares = makeWhiteFrontRank();

        for (int file = 0; file < BOARD_FILE; file++) {
            boardImage.put(Coordinate.fromOnBoard(WHITE_BACK_RANK, file), backSquares.get(file));
            boardImage.put(Coordinate.fromOnBoard(WHITE_FRONT_RANK, file), frontSquares.get(file));
        }
    }

    private static List<Square> makeBackRank(Camp camp) {
        List<Piece> frontPieces = List.of(
                new Rook(), new Knight(), new Bishop(),
                new Queen(), new King(), new Bishop(),
                new Knight(), new Rook()
        );

        return frontPieces.stream()
                          .map(pieceType -> new ConcreteSquare(pieceType, camp))
                          .collect(Collectors.toList());
    }

    private static List<Square> makeWhiteFrontRank() {
        return IntStream.range(0, 8)
                        .mapToObj(i -> new ConcreteSquare(new WhitePawn(), Camp.WHITE))
                        .collect(Collectors.toList());
    }

    private static void addEmptyRanks(Map<Coordinate, Square> boardImage) {
        for (int rank = START_EMPTY_RANK; rank <= END_EMPTY_RANK; rank++) {
            addEachEmptyRank(boardImage, rank);
        }
    }

    private static void addEachEmptyRank(Map<Coordinate, Square> boardImage, int rank) {
        for (int file = 0; file < BOARD_FILE; file++) {
            boardImage.put(Coordinate.fromOnBoard(rank, file), new EmptySquare());
        }
    }

    private static void addBlackRanks(Map<Coordinate, Square> boardImage) {
        List<Square> backSquares = makeBackRank(Camp.BLACK);
        List<Square> frontSquares = makeBlackFrontRank();

        for (int file = 0; file < BOARD_FILE; file++) {
            boardImage.put(Coordinate.fromOnBoard(BLACK_FRONT_RANK, file), frontSquares.get(file));
            boardImage.put(Coordinate.fromOnBoard(BLACK_BACK_RANK, file), backSquares.get(file));
        }
    }

    private static List<Square> makeBlackFrontRank() {
        return IntStream.range(0, 8)
                        .mapToObj(i -> new ConcreteSquare(new BlackPawn(), Camp.BLACK))
                        .collect(Collectors.toList());
    }

    public static Map<Coordinate, Square> generate() {
        return Map.copyOf(boardImage);
    }
}
