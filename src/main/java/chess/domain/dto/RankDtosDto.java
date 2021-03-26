package chess.domain.dto;

import java.util.List;

public class RankDtosDto implements ChessDto {

    private final List<RankDto> rankDtos;

    public RankDtosDto(final List<RankDto> rankDtos) {
        this.rankDtos = rankDtos;
    }

    public List<RankDto> getRankDtos() {
        return rankDtos;
    }
}
