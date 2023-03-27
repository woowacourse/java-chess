package util;

import domain.game.Position;

public class PositionMapper {
    public static String convertPositionToString(Position position) {
        String fileText = FileMapper.convertFileToText(position.getFile());
        String rankText = RankMapper.convertRankToText(position.getRank());
        return String.join("", fileText, rankText);
    }
}
