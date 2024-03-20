package chess.dto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import chess.model.Board;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private final List<RankDto> ranks;

    public BoardDto(List<RankDto> rank) {
        this.ranks = rank;
    }

    public static BoardDto from(Board board) {
        List<RankDto> ranks = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            ranks.add(RankDto.of(board, i));
        }
        return new BoardDto(ranks);
    }

    @Override
    public String toString() {
        return ranks.stream()
                .map(RankDto::toString)
                .collect(collectingAndThen(toList(), list -> String.join("\n", list)));
    }
}
