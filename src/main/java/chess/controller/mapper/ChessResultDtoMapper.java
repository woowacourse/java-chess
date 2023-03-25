package chess.controller.mapper;

import chess.controller.dto.ChessResultDto;
import chess.domain.chess.vo.ScoreVO;

public class ChessResultDtoMapper {
    public static ChessResultDto from(final ScoreVO scoreVO) {
        return new ChessResultDto(scoreVO.getWhiteScore().getScore(), scoreVO.getBlackScore().getScore());
    }
}
