package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.SquareState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChessBoardFormatter {
    public static final int RANK_SIZE = 8;

    private final List<String> chessBoardMark;

    private ChessBoardFormatter(final List<String> chessBoardMark) {
        this.chessBoardMark = chessBoardMark;
    }

    public static ChessBoardFormatter toString(final ChessBoard chessBoard) {
        return new ChessBoardFormatter(getChessBoardMark(chessBoard));
    }

    private static List<String> getChessBoardMark(final ChessBoard chessBoard) {
        final List<String> currentChessBoardMark = new ArrayList<>();
        final List<SquareState> squares = chessBoard.getSquares();

        for (int rank = 0; rank < squares.size(); rank += RANK_SIZE) {
            currentChessBoardMark.add(makeRankMark(squares.subList(rank, rank + RANK_SIZE)));
        }

        Collections.reverse(currentChessBoardMark);
        return currentChessBoardMark;
    }

    private static String makeRankMark(final List<SquareState> rank) {
        StringBuilder currentRank = new StringBuilder();
        for (SquareState square : rank) {
            currentRank.append(SquareMark.getMarkBySquare(square));
        }
        return currentRank.toString();
    }

    public List<String> getChessBoardMark() {
        return Collections.unmodifiableList(chessBoardMark);
    }
}
