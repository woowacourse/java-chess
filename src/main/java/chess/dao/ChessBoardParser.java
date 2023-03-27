package chess.dao;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.SquareState;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardParser {
    public static final int RANK_SIZE = 8;

    private final ChessBoard chessBoard;

    private ChessBoardParser(final Map<SquareCoordinate, SquareState> squares) {
        this.chessBoard = new ChessBoard(squares);
    }

    public static ChessBoardParser from(final String oneLineChessBoardMark) {
        return new ChessBoardParser(parseChessBoard(oneLineChessBoardMark));
    }

    private static Map<SquareCoordinate, SquareState> parseChessBoard(final String oneLineChessBoardMark) {
        final Map<SquareCoordinate, SquareState> squares = new LinkedHashMap<>();

        final char rank = '1';
        for (int i = RANK_SIZE - 1; i >= 0; i--) {
            parseChessBoardEachRank(squares, oneLineChessBoardMark.substring(i * RANK_SIZE, i * RANK_SIZE + 8), (char) (rank + i));
        }

        return squares;
    }

    private static void parseChessBoardEachRank(final Map<SquareCoordinate, SquareState> squares, final String rankMark, final char rank) {

        final char file = 'a';
        for (int i = 0; i < RANK_SIZE; i++) {
            squares.put(SquareCoordinate.of(makeAlphanumeric((char) (file + i), rank)), SquareParser.getSquareStateByMark(rankMark.substring(i, i + 1)));
        }
    }

    private static String makeAlphanumeric(final char file, final char rank) {
        return String.valueOf(file) + rank;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
