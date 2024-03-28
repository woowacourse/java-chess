package chess.dto;

import chess.model.board.Board;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public record BoardDTO(List<RankDTO> board) {
    private static final List<Integer> RANK_ORDERS = IntStream.rangeClosed(Board.MIN_LENGTH, Board.MAX_LENGTH)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .toList();

    public static BoardDTO from(Board board) {
        List<RankDTO> ranks = RANK_ORDERS.stream()
                .map(board::getRank)
                .map(RankDTO::of)
                .toList();
        return new BoardDTO(ranks);
    }
}
