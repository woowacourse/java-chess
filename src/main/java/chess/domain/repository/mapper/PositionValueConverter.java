package chess.domain.repository.mapper;

import chess.domain.position.Position;

public class PositionValueConverter {

    private PositionValueConverter() {
    }

    public static String convertToValue(Position position) {
        String fileValue = FileDtoMapper.convertToFileValue(position.getFile());
        String rankValue = RankDtoMapper.convertToRankValue(position.getRank());
        return fileValue + rankValue;
    }
}
