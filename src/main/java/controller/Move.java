package controller;

import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import java.util.List;
import view.ColumnConverter;

public final class Move {

    private static final int START_INDEX = 1;
    private static final int END_INDEX = 2;
    private static final int MOVE_COMMAND_COUNT = 3;
    private static final int LOCATION_INPUT_LENGTH = 2;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    
    private final Location start;
    private final Location end;

    private Move(final Location start, final Location end) {
        this.start = start;
        this.end = end;
    }

    public static Move of(final List<String> commands) {
        if (commands.size() != MOVE_COMMAND_COUNT) {
            throw new IllegalArgumentException("잘못된 이동 입력입니다.");
        }
        final Location startLocation = convertToLocation(commands.get(START_INDEX));
        final Location endLocation = convertToLocation(commands.get(END_INDEX));
        return new Move(startLocation, endLocation);
    }

    private static Location convertToLocation(final String locationInput) {
        if (locationInput.length() != LOCATION_INPUT_LENGTH) {
            throw new IllegalArgumentException("이동 위치에 대한 입력이 잘못 되었습니다.");
        }
        try {
            return Location.of(
                Row.valueOf(locationInput.charAt(ROW_INDEX) - '0' - 1),
                Column.valueOf(ColumnConverter.findColumn(locationInput.charAt(COLUMN_INDEX)))
            );
        } catch (Exception exception) {
            throw new IllegalArgumentException("이동 위치에 대한 입력이 잘못 되었습니다.");
        }
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }
}
