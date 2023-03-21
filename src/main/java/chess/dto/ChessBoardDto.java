package chess.dto;

import chess.controller.SquareMark;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChessBoardDto {
    public static final int RANK_SIZE = 8;

    private final List<String> chessBoardMark;

    private ChessBoardDto(final List<String> gameStatus) {
        this.chessBoardMark = gameStatus;
    }

    public static ChessBoardDto from(final ChessBoard chessBoard) {
        return new ChessBoardDto(getChessBoardMark(chessBoard));
    }

    private static List<String> getChessBoardMark(final ChessBoard chessBoard) {
        final List<String> currentChessBoardMark = new ArrayList<>();
        final List<Square> squares = chessBoard.getSquares();

        for (int rank = 0; rank < squares.size(); rank += RANK_SIZE) {
            currentChessBoardMark.add(makeRankMark(squares.subList(rank, rank + RANK_SIZE)));
        }

        Collections.reverse(currentChessBoardMark);
        return currentChessBoardMark;
    }

    private static String makeRankMark(final List<Square> rank) {
        StringBuilder currentRank = new StringBuilder();
        for (Square square : rank) {
            currentRank.append(SquareMark.getMarkBySquare(square));
        }
        return currentRank.toString();
    }

    public List<String> getChessBoardMark() {
        return Collections.unmodifiableList(chessBoardMark);
    }
}
