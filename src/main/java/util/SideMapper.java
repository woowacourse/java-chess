package util;

import domain.game.Side;
import java.util.Arrays;

public enum SideMapper {
    WHITE("WHITE", Side.WHITE),
    BLACK("BLACK", Side.BLACK);

    private final String text;
    private final Side side;

    SideMapper(String text, Side side) {
        this.text = text;
        this.side = side;
    }

    public static Side convertTextToSide(String text) {
        return Arrays.stream(SideMapper.values())
                .filter(sideMapper -> sideMapper.text.equals(text))
                .findFirst()
                .map(sideMapper -> sideMapper.side)
                .orElseThrow(() -> new IllegalStateException("서버 내부 에러 - 존재하지 않는 Side를 매핑했습니다."));
    }
}
