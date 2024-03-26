package chess.dto;

import chess.model.board.Board;
import chess.model.position.Row;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private static final int MAX_INDEX = 7;

    private final List<RankDto> ranks;

    public BoardDto(List<RankDto> rank) {
        this.ranks = rank;
    }

    public static BoardDto from(Board board) {
        List<RankDto> ranks = new ArrayList<>();
        for (int i = 0; i <= MAX_INDEX; i++) {
            Row row = Row.findRow(i);
            ranks.add(RankDto.of(board, row));
        }
        return new BoardDto(ranks);
    }

    public List<RankDto> getRanks() {
        return ranks;
    }
}
