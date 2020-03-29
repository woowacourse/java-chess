package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import domain.pieces.Pieces;
import domain.point.Point;
import domain.team.Team;
import java.util.Arrays;
import java.util.List;

public class Playing extends GameState {

    public Playing(Pieces pieces) {
        super(pieces);
    }

    @Override
    public StateStrategy start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StateStrategy end() {
        return new Finished(pieces);
    }

    @Override
    public StateStrategy move(Team turn, String input) {
        validate(input);
        List<String> splitInput = Arrays.asList(input.split(" "));
        Point from = Point.of(splitInput.get(1));
        Point to = Point.of(splitInput.get(2));
        pieces.move(turn, from, to);
        return this;
    }

    @Override
    public StateStrategy status() {
        return this;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private void validate(String input) {
        validateSizeThree(input);
        validateEachSizeTwo(input);
    }

    private void validateSizeThree(String input) {
        if (Arrays.asList(input.split(" ")).size() != 3) {
            throw new CommendTypeException("잘못된 입력입니다.");
        }
    }

    private void validateEachSizeTwo(String input) {
        List<String> splitInput = Arrays.asList(input.split(" "));
        if (!(splitInput.get(1).length() == 2 && splitInput.get(2).length() == 2)) {
            throw new CommendTypeException("잘못된 입력입니다.");
        }
    }
}
