package dto;

import java.util.Map;

public record BoardDto(Map<PointDto, String> map) {
    public static final int VERTICAL_START_INDEX = 7;
    public static final int HORIZONTAL_END_INDEX = 8;
    private static final String EMPTY_POINT_VALUE = ".";

    public String getWithVerticalAndHorizontal(final int vertical, final int horizontal) {
        return map.getOrDefault(new PointDto(vertical, horizontal), EMPTY_POINT_VALUE);
    }
}
