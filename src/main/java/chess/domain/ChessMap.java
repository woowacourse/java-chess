package chess.domain;

import chess.domain.piece.Piece;
import java.util.List;

public class ChessMap {

    private static final char BLANK = '.';

    private final char[][] chessMap;

    private ChessMap(char[][] chessMap) {
        this.chessMap = chessMap;
    }

    public static ChessMap of(final List<Piece> whitePieces, final List<Piece> blackPieces) {
        final char[][] chessMap = initializeChessMap();

        showWhitePieces(chessMap, whitePieces);
        showBlackPieces(chessMap, blackPieces);

        return new ChessMap(chessMap);
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

    private static void showWhitePieces(final char[][] chessMap, final List<Piece> whitePieces) {
        for (Piece piece : whitePieces) {
            final Position position = piece.getPosition();
            final int rank = 8 - position.getRank();
            final int file = position.getFile();
            chessMap[rank][file] = piece.getName();
        }
    }

    private static void showBlackPieces(final char[][] chessMap, final List<Piece> blackPieces) {
        for (Piece piece : blackPieces) {
            final Position position = piece.getPosition();
            final int rank = 8 - position.getRank();
            final int file = position.getFile();
            chessMap[rank][file] = Character.toLowerCase(piece.getName());
        }
    }

    public char[][] getChessMap() {
        return chessMap;
    }
}
