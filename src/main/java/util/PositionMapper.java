package util;

import domain.game.Position;

public class PositionMapper {
    public static String convertPositionToText(Position position) {
        String fileText = FileMapper.convertFileToText(position.getFile());
        String rankText = RankMapper.convertRankToText(position.getRank());
        return String.join("", fileText, rankText);
    }

    public static Position convertTextToPosition(String positionText) {
        String fileText = positionText.substring(0, 1);
        String rankText = positionText.substring(1, 2);
        return Position.of(FileMapper.convertTextToFile(fileText), RankMapper.convertTextToRank(rankText));
    }
}
