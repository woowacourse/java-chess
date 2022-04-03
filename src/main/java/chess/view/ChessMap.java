package chess.view;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.List;

public class ChessMap {

    private static final char BLANK = '.';
    private static final int CHESS_MAP_RANK_SIZE = 8;

    private final char[][] chessMap;
    private final boolean isRunning;

    private ChessMap(char[][] chessMap, boolean isRunning) {
        this.chessMap = chessMap;
        this.isRunning = isRunning;
    }

    public static ChessMap of(final List<Piece> whitePieces, final List<Piece> blackPieces) {
        final char[][] chessMap = initializeChessMap();

        markWhitePieces(chessMap, whitePieces);
        markBlackPieces(chessMap, blackPieces);
        boolean isRunning = checkRunning(whitePieces) && checkRunning(blackPieces);

        return new ChessMap(chessMap, isRunning);
    }

    private static char[][] initializeChessMap() {
        return new char[][]{
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
                {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK},
        };
    }

    private static void markWhitePieces(final char[][] chessMap, final List<Piece> whitePieces) {
        for (Piece piece : whitePieces) {
            final Position position = piece.getPosition();
            final int rank = CHESS_MAP_RANK_SIZE - position.getRank();
            final int file = position.getFile();
            chessMap[rank][file] = Character.toLowerCase(piece.getName());
        }
    }

    private static void markBlackPieces(final char[][] chessMap, final List<Piece> blackPieces) {
        for (Piece piece : blackPieces) {
            final Position position = piece.getPosition();
            final int rank = CHESS_MAP_RANK_SIZE - position.getRank();
            final int file = position.getFile();
            chessMap[rank][file] = piece.getName();
        }
    }

    private static boolean checkRunning(final List<Piece> pieces) {
        return pieces.stream()
                .anyMatch(Piece::isKing);
    }

    public char[][] getChessMap() {
        return chessMap;
    }
}
