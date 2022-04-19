package chess.domain.board.factory;

import static chess.domain.piece.PieceTeam.BLACK;
import static chess.domain.piece.PieceTeam.EMPTY;
import static chess.domain.piece.PieceTeam.WHITE;

import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptySpace;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class StringBoardFactory extends BoardFactory {

    private static final int BOARD_RANK_SIZE = 8;
    private static final int BOARD_FILE_SIZE = 8;
    private static final String EMPTY_SPACE_SYMBOL = ".";
    private static final List<String> UPPER_CASE_PIECE_SYMBOLS = List.of("R", "N", "B", "Q", "K", "P", EMPTY_SPACE_SYMBOL);
    private static final Map<String, Supplier<? extends Piece>> whitePieceCreator;
    private static final Map<String, Supplier<? extends Piece>> blackPieceCreator;
    private static final Supplier<? extends Piece> emptySpaceCreator = () -> new EmptySpace(EMPTY);

    static final String INVALID_RANK_SIZE_MESSAGE = "RANK 크기가 올바르지 않습니다.";
    static final String INVALID_FILE_SIZE_MESSAGE = "FILE 크기가 올바르지 않습니다.";
    static final String NOW_ALLOWED_SYMBOL_MESSAGE = "허용되지 않는 문자가 있습니다";

    private final List<String> stringChessBoard;

    static {
        whitePieceCreator = initWhitePieceCreator();
        blackPieceCreator = initBlackPieceCreator();
    }

    private StringBoardFactory(List<String> stringChessBoard) {
        this.stringChessBoard = stringChessBoard;
    }

    private static Map<String, Supplier<? extends Piece>> initWhitePieceCreator() {
        Map<String, Supplier<? extends Piece>> pieceCreator = new HashMap<>();

        pieceCreator.put("r", () -> new Rook(WHITE));
        pieceCreator.put("n", () -> new Knight(WHITE));
        pieceCreator.put("b", () -> new Bishop(WHITE));
        pieceCreator.put("q", () -> new Queen(WHITE));
        pieceCreator.put("k", () -> new King(WHITE));
        pieceCreator.put("p", () -> new Pawn(WHITE));

        return pieceCreator;
    }

    private static Map<String, Supplier<? extends Piece>> initBlackPieceCreator() {
        Map<String, Supplier<? extends Piece>> pieceCreator = new HashMap<>();

        pieceCreator.put("R", () -> new Rook(BLACK));
        pieceCreator.put("N", () -> new Knight(BLACK));
        pieceCreator.put("B", () -> new Bishop(BLACK));
        pieceCreator.put("Q", () -> new Queen(BLACK));
        pieceCreator.put("K", () -> new King(BLACK));
        pieceCreator.put("P", () -> new Pawn(BLACK));

        return pieceCreator;
    }

    public static BoardFactory getInstance(List<String> stringChessBoard) {
        validateChessBoardSize(stringChessBoard);

        return new StringBoardFactory(stringChessBoard);
    }

    private static void validateChessBoardSize(List<String> stringChessBoard) {
        validateRankSize(stringChessBoard);
        validateFileSize(stringChessBoard);
        validateSymbol(stringChessBoard);
    }

    private static void validateRankSize(List<String> stringChessBoard) {
        if (stringChessBoard.size() != BOARD_RANK_SIZE) {
            throw new IllegalArgumentException(INVALID_RANK_SIZE_MESSAGE);
        }
    }

    private static void validateFileSize(List<String> stringChessBoard) {
        boolean noneMatchFileSize = stringChessBoard
                .stream()
                .anyMatch(file -> file.length() != BOARD_FILE_SIZE);

        if (noneMatchFileSize) {
            throw new IllegalArgumentException(INVALID_FILE_SIZE_MESSAGE);
        }
    }

    private static void validateSymbol(List<String> stringChessBoard) {
        boolean noneMatchSymbol = stringChessBoard
                .stream()
                .flatMap(file -> Arrays.stream(file.split("")))
                .map(String::toUpperCase)
                .anyMatch(pieceSymbol -> !UPPER_CASE_PIECE_SYMBOLS.contains(pieceSymbol) &&
                        !pieceSymbol.equals(EMPTY_SPACE_SYMBOL));

        if (noneMatchSymbol) {
            throw new IllegalArgumentException(NOW_ALLOWED_SYMBOL_MESSAGE);
        }
    }

    @Override
    public Map<Position, Piece> create() {

        Map<Position, Piece> board = new HashMap<>();

        makeBoardForRank(board);

        return board;
    }

    private void makeBoardForRank(Map<Position, Piece> board) {
        for (int rankCount = 1; rankCount <= stringChessBoard.size(); rankCount++) {
            makeBoardForFile(board, rankCount);
        }
    }

    private void makeBoardForFile(Map<Position, Piece> board, int rankCount) {

        String stringRank = stringChessBoard.get(BOARD_RANK_SIZE - rankCount);

        for (int fileCount = 1; fileCount <= stringRank.length(); fileCount++) {

            String pieceSymbol = String.valueOf(stringRank.charAt(fileCount - 1));
            Piece createdPiece = createPiece(pieceSymbol);
            Position createdPosition = Position.of(fileCount, rankCount);

            board.put(createdPosition, createdPiece);
        }
    }

    private Piece createPiece(String pieceSymbol) {
        if (pieceSymbol.equals(EMPTY_SPACE_SYMBOL)) {
            return emptySpaceCreator.get();
        }

        if (isUpperCase(pieceSymbol)) {
            return blackPieceCreator.get(pieceSymbol).get();
        }

        return whitePieceCreator.get(pieceSymbol).get();
    }

    private boolean isUpperCase(String pieceSymbol) {
        return UPPER_CASE_PIECE_SYMBOLS.contains(pieceSymbol);
    }
}
