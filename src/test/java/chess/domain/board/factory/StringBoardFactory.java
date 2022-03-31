package chess.domain.board.factory;

import chess.domain.board.position.Position;
import chess.domain.board.position.Positions;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StringBoardFactory extends BoardFactory {

    private static final int BOARD_RANK_SIZE = 8;
    private static final int BOARD_FILE_SIZE = 8;
    private static final List<String> PIECE_SYMBOLS = List.of("R", "N", "B", "Q", "K", "P",".");
    private static final Map<String, Function<PieceColor, ? extends Piece>> pieceCreator = new HashMap<>();

    static final String INVALID_RANK_SIZE_MESSAGE = "RANK 크기가 올바르지 않습니다.";
    static final String INVALID_FILE_SIZE_MESSAGE = "FILE 크기가 올바르지 않습니다.";
    static final String NOW_ALLOWED_SYMBOL_MESSAGE = "허용되지 않는 문자가 있습니다";

    private final List<String> stringChessBoard;

    static {
        pieceCreator.put("R", Rook::new);
        pieceCreator.put("N", Knight::new);
        pieceCreator.put("B", Bishop::new);
        pieceCreator.put("Q", Queen::new);
        pieceCreator.put("K", King::new);
        pieceCreator.put("P", Pawn::new);
        pieceCreator.put(".", EmptyPiece::new);
    }

    private StringBoardFactory(List<String> stringChessBoard) {
        this.stringChessBoard = stringChessBoard;
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
                .anyMatch(symbol -> !PIECE_SYMBOLS.contains(symbol));

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

        String stringRank = stringChessBoard.get(rankCount - 1);

        for (int fileCount = 1; fileCount <= stringRank.length(); fileCount++) {

            String square = String.valueOf(stringRank.charAt(fileCount - 1));

            Piece createdPiece = pieceCreator.get(square).apply(PieceColor.BLACK);
            Position createdPosition = Positions.findPositionBy(fileCount, rankCount);

            board.put(createdPosition, createdPiece);
        }
    }
}
