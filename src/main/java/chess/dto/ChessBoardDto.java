package chess.dto;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class ChessBoardDto {

    public static final int RANK_SIZE = 8;

    private final List<List<String>> chessBoard;

    public ChessBoardDto(final ChessBoard chessBoard) {
        this.chessBoard = getChessBoardMark(chessBoard);
    }

    private List<List<String>> getChessBoardMark(final ChessBoard chessBoard) {
        final List<List<String>> currentChessBoardMark = new ArrayList<>();
        final List<Square> squares = Optional.of(chessBoard.getSquares()).orElse(Collections.emptyList());

        for (int rank = 0; rank < squares.size(); rank += RANK_SIZE) {
            currentChessBoardMark.add(getRankMark(squares.subList(rank, rank + RANK_SIZE)));
        }

        Collections.reverse(currentChessBoardMark);
        return currentChessBoardMark;
    }

    private List<String> getRankMark(final List<Square> rank) {
        List<String> currentRank = new ArrayList<>();
        for (Square square : rank) {
            currentRank.add(SquareMark.getMarkBySquare(square));
        }
        return currentRank;
    }

    public List<List<String>> getChessBoard() {
        return this.chessBoard;
    }
}
