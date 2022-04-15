package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

public class ChessMap {

    private static final char BLANK = '.';
    private static final int CHESS_LENGTH = 8;

    private final char[][] chessMap;

    private ChessMap(char[][] chessMap) {
        this.chessMap = chessMap;
    }

    public static ChessMap of(final List<Piece> whitePieces, final List<Piece> blackPieces) {
        final char[][] chessMap = initializeChessMap();

        markWhitePieces(chessMap, whitePieces);
        markBlackPieces(chessMap, blackPieces);

        return new ChessMap(chessMap);
    }

    private static char[][] initializeChessMap() {
        char[][] chessMap = new char[CHESS_LENGTH][CHESS_LENGTH];
        for (int rank = 0; rank < CHESS_LENGTH; rank++) {
            markBlank(chessMap, rank);
        }
        return chessMap;
    }

    private static void markBlank(char[][] chessMap, int rank) {
        for (int file = 0; file < CHESS_LENGTH; file++) {
            chessMap[rank][file] = BLANK;
        }
    }

    private static void markWhitePieces(final char[][] chessMap, final List<Piece> whitePieces) {
        for (Piece piece : whitePieces) {
            final Position position = piece.getPosition();
            final int rank = 8 - position.getRank().getValue();
            final int file = position.getFile().getValue() - 'a';
            chessMap[rank][file] = Character.toLowerCase(piece.getName());
        }
    }

    private static void markBlackPieces(final char[][] chessMap, final List<Piece> blackPieces) {
        for (Piece piece : blackPieces) {
            final Position position = piece.getPosition();
            final int rank = 8 - position.getRank().getValue();
            final int file = position.getFile().getValue() - 'a';
            chessMap[rank][file] = piece.getName();
        }
    }

    public char[][] getChessMap() {
        return chessMap;
    }
}
