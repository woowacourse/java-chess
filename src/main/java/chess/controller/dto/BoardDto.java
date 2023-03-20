package chess.controller.dto;

import chess.domain.Board;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardDto {
    
    private final List<RankDto> ranks;

    private BoardDto(List<RankDto> ranks) {
        this.ranks = ranks;
    }

    public static BoardDto create(Board board) {
        List<RankDto> rankDtos = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            RankDto rankDto = RankDto.create(board.getPiecesAt(rank));
            rankDtos.add(rankDto);
        }
        Collections.reverse(rankDtos);
        return new BoardDto(rankDtos);
    }
    
    public List<RankDto> getRanks() {
        return this.ranks;
    }
}
