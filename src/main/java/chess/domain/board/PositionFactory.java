package chess.domain.board;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public class PositionFactory {
    private static final int START = 1;
    private static final int END = 8;
    private final static List<Position> positions = IntStream.rangeClosed(START,END).mapToObj(Position::new).collect(Collectors.toList());;

    public static Position moveBack(int position, int moveCnt) {
        int target = position - moveCnt;
        if (target < START) {
            return positions.get(convertToIndex(position));
        }

        return positions.get(convertToIndex(target));
    }

    public static Position moveGo(int position, int moveCnt) {
        int target = position + moveCnt;
        if (target > END) {
            return positions.get(convertToIndex(position));
        }
        return positions.get(convertToIndex(target));
    }

    public static List<Position> moveGoToEnd(int position) {
        List<Position> positionList = new ArrayList<>();
        int target = position + 1;

        while (target <= END) {
            positionList.add(positions.get(convertToIndex(target)));
            target++;
        }
        return positionList;
    }

    public static List<Position> moveBackToEnd(int position) {
        List<Position> positionList = new ArrayList<>();
        int target = position - 1;

        while (target >= START) {
            positionList.add(positions.get(convertToIndex(target)));
            target--;
        }
        return positionList;
    }

    private static int convertToIndex(int position) {
        return position - 1;
    }

    public static Position of(int index) {
        return positions.get(index);
    }
}
