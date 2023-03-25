package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
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
        boardImage.putAll(makeWhiteRanks());
        boardImage.putAll(makeEmptyRanks());
        boardImage.putAll(makeBlackRanks());
        return boardImage;
    }

    private static Map<Coordinate, Piece> makeWhiteRanks() {
        List<Piece> backPieces = makeBackRank(Camp.WHITE);
        List<Piece> frontPieces = makeWhiteFrontRank();

        Map<Coordinate, Piece> whiteBoardImage = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            whiteBoardImage.put(Coordinate.fromOnBoard(WHITE_BACK_RANK, file), backPieces.get(file));
            whiteBoardImage.put(Coordinate.fromOnBoard(WHITE_FRONT_RANK, file), frontPieces.get(file));
        }
        return whiteBoardImage;
    }

    private static List<Piece> makeBackRank(final Camp camp) {
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

    private static Map<Coordinate, Piece> makeEmptyRanks() {
        Map<Coordinate, Piece> emptyBoardImage = new HashMap<>();
        for (int rank = START_EMPTY_RANK; rank <= END_EMPTY_RANK; rank++) {
            emptyBoardImage.putAll(makeEachEmptyRank(rank));
        }
        return emptyBoardImage;
    }

    private static Map<Coordinate, Piece> makeEachEmptyRank(final int rank) {
        Map<Coordinate, Piece> emptyRank = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            emptyRank.put(Coordinate.fromOnBoard(rank, file), new EmptyPiece());
        }

        return emptyRank;
    }

    private static Map<Coordinate, Piece> makeBlackRanks() {
        List<Piece> backPieces = makeBackRank(Camp.BLACK);
        List<Piece> frontPieces = makeBlackFrontRank();

        Map<Coordinate, Piece> blackboardImage = new HashMap<>();
        for (int file = 0; file < BOARD_FILE; file++) {
            blackboardImage.put(Coordinate.fromOnBoard(BLACK_FRONT_RANK, file), frontPieces.get(file));
            blackboardImage.put(Coordinate.fromOnBoard(BLACK_BACK_RANK, file), backPieces.get(file));
        }
        return blackboardImage;
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
