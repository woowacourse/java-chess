package domain.board;

import domain.piece.Piece;
import domain.piece.move.Coordinate;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.pawn.BlackInitPawn;
import domain.piece.pawn.WhiteInitPawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import domain.square.Color;
import domain.square.Square;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BoardInitialImage {

    public static final int FILE_SIZE = 8;
    public static final int EMPTY_RANK_SIZE = 4;

    private static final Map<Coordinate, Square> initializedBoardImage;
    private static Coordinate iterator;

    static {
        initializedBoardImage = new HashMap<>();
        makeBoardImage();
    }

    private static void makeBoardImage() {
        setUpIterator();
        makeKingExistRank(Color.WHITE);
        makeWhitePawnExistRank();
        makeEmptyRanks();
        makeBlackPawnExistRank();
        makeKingExistRank(Color.BLACK);
    }

    private static void setUpIterator() {
        iterator = new Coordinate(0, 0);
    }

    private static void makeKingExistRank(final Color color) {
        List<Piece> frontPieces = List.of(
                new Rook(), new Knight(), new Bishop(), new Queen(),
                new King(), new Bishop(), new Knight(), new Rook()
        );
        for (Piece piece : frontPieces) {
            initializedBoardImage.put(iterator, new Square(piece, color));
            updateIterator();
        }
    }

    private static void makeWhitePawnExistRank() {
        for (int file = 0; file < FILE_SIZE; file++) {
            initializedBoardImage.put(iterator, new Square(new WhiteInitPawn(), Color.WHITE));
            updateIterator();
        }
    }

    private static void makeEmptyRanks() {
        for (int rank = 0; rank < EMPTY_RANK_SIZE; rank++) {
            makeEmptyRank();
        }
    }

    private static void makeEmptyRank() {
        for (int file = 0; file < FILE_SIZE; file++) {
            initializedBoardImage.put(iterator, Square.ofEmptyPiece());
            updateIterator();
        }
    }

    private static void makeBlackPawnExistRank() {
        for (int file = 0; file < FILE_SIZE; file++) {
            initializedBoardImage.put(iterator, new Square(new BlackInitPawn(), Color.BLACK));
            updateIterator();
        }
    }

    private static void updateIterator() {
        int updatedRow = iterator.getRow() + (iterator.getCol() + 1) / FILE_SIZE;
        int updatedCol = (iterator.getCol() + 1) % FILE_SIZE;

        iterator = new Coordinate(updatedRow, updatedCol);
    }

    public static Map<Coordinate, Square> getCachedBoard() {
        return new HashMap<>(initializedBoardImage);
    }
}
