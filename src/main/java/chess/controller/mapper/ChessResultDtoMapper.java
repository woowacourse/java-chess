package chess.controller.mapper;

import chess.controller.dto.ChessResultDto;
import chess.domain.chess.vo.ChessScore;

public class ChessResultDtoMapper {
    public static ChessResultDto from(final ChessScore chessScore) {
        return new ChessResultDto(chessScore.getWhiteScore().getScore(), chessScore.getBlackScore().getScore());
    }
}
