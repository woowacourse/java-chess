package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import domain.pieces.Pieces;
import domain.point.Column;
import domain.point.Point;
import domain.point.Row;
import java.util.Arrays;
import java.util.List;

public class Move {
    public static void movePiece(Pieces pieces, String input) {
        validate(input);
        List<String> splitInput = Arrays.asList(input.split(" "));
        Point from = createPointBefore(splitInput.get(1));
        Point to = createPointAfter(splitInput.get(2));
    }

    private static Point createPointBefore(String location) {
        return new Point(Row.find(location.substring(0, 1)), Column.find(location.substring(1, 2)));
    }

    private static Point createPointAfter(String location) {
        return new Point(Row.find(location.substring(0, 1)), Column.find(location.substring(1, 2)));
    }

    private static void validate(String input) {
        validateSizeThree(input);
        validateEachSizeTwo(input);
    }

    private static void validateSizeThree(String input) {
        if (Arrays.asList(input.split(" ")).size() != 3) {
            throw new CommendTypeException("잘못된 입력입니다.");
        }
    }

    private static void validateEachSizeTwo(String input) {
        List<String> splitInput = Arrays.asList(input.split(" "));
        if (!(splitInput.get(1).length() == 2 && splitInput.get(2).length() == 2)) {
            throw new CommendTypeException("잘못된 입력입니다.");
        }
    }
}
