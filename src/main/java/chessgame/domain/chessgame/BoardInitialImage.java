package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Camp;
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

    private static final Map<Coordinate, PieceType> boardImage;

    private BoardInitialImage() {
    }

    static {
        boardImage = makeBoardImage();
    }

    private static Map<Coordinate, PieceType> makeBoardImage() {
        Map<Coordinate, PieceType> boardImage = new HashMap<>();
        boardImage.putAll(makeWhiteRanks());
        boardImage.putAll(makeEmptyRanks());
        boardImage.putAll(makeBlackRanks());
        return boardImage;
    }

    private static Map<Coordinate, PieceType> makeWhiteRanks() {
        List<PieceType> backPieces = makeBackRank(Camp.WHITE);
        List<PieceType> frontPieces = makeWhiteFrontRank();

        Map<Coordinate, PieceType> whiteBoardImage = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            whiteBoardImage.put(Coordinate.fromOnBoard(WHITE_BACK_RANK, file), backPieces.get(file));
            whiteBoardImage.put(Coordinate.fromOnBoard(WHITE_FRONT_RANK, file), frontPieces.get(file));
        }
        return whiteBoardImage;
    }

    private static List<PieceType> makeBackRank(final Camp camp) {
        return List.of(
                new Rook(camp), new Knight(camp), new Bishop(camp),
                new Queen(camp), new King(camp), new Bishop(camp),
                new Knight(camp), new Rook(camp)
        );
    }

    private static List<PieceType> makeWhiteFrontRank() {
        return IntStream.range(0, 8)
                        .mapToObj(i -> new WhitePawn())
                        .collect(Collectors.toList());
    }

    private static Map<Coordinate, PieceType> makeEmptyRanks() {
        Map<Coordinate, PieceType> emptyBoardImage = new HashMap<>();
        for (int rank = START_EMPTY_RANK; rank <= END_EMPTY_RANK; rank++) {
            emptyBoardImage.putAll(makeEachEmptyRank(rank));
        }
        return emptyBoardImage;
    }

    private static Map<Coordinate, PieceType> makeEachEmptyRank(final int rank) {
        Map<Coordinate, PieceType> emptyRank = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            emptyRank.put(Coordinate.fromOnBoard(rank, file), new Empty());
        }

        return emptyRank;
    }

    private static Map<Coordinate, PieceType> makeBlackRanks() {
        List<PieceType> backPieces = makeBackRank(Camp.BLACK);
        List<PieceType> frontPieces = makeBlackFrontRank();

        Map<Coordinate, PieceType> blackboardImage = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            blackboardImage.put(Coordinate.fromOnBoard(BLACK_FRONT_RANK, file), frontPieces.get(file));
            blackboardImage.put(Coordinate.fromOnBoard(BLACK_BACK_RANK, file), backPieces.get(file));
        }
        return blackboardImage;
    }

    private static List<PieceType> makeBlackFrontRank() {
        return IntStream.range(0, 8)
                        .mapToObj(i -> new BlackPawn())
                        .collect(Collectors.toList());
    }

    public static Map<Coordinate, PieceType> generate() {
        return Map.copyOf(boardImage);
    }
}
