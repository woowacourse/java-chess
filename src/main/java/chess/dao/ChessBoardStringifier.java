package chess.dao;

import chess.controller.SquareMark;
import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.SquareState;

import java.util.List;

public class ChessBoardStringifier {
    public static final int RANK_SIZE = 8;

    public static String stringifyChessBoard(final ChessBoard chessBoard) {
        StringBuilder oneLineChessBoard = new StringBuilder();
        final List<SquareState> squares = chessBoard.getSquares();

        for (int rank = squares.size(); rank > 0; rank -= RANK_SIZE) {
            oneLineChessBoard.append(stringifyRank(squares.subList(rank - RANK_SIZE, rank)));
        }

        return oneLineChessBoard.toString();
    }

    private static String stringifyRank(final List<SquareState> rank) {
        StringBuilder currentRank = new StringBuilder();
        for (SquareState square : rank) {
            currentRank.append(SquareMark.getMarkBySquare(square));
        }
        return currentRank.toString();
    }
}
