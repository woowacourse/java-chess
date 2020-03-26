package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import domain.pieces.Pieces;
import domain.point.Point;
import domain.team.Team;
import java.util.Arrays;
import java.util.List;

public class Move {
    public static void movePiece(Team turn, Pieces pieces, String input) {
        validate(input);
        List<String> splitInput = Arrays.asList(input.split(" "));
        Point from = Point.of(splitInput.get(1));
        Point to = Point.of(splitInput.get(2));
        pieces.move(turn, from, to);
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
