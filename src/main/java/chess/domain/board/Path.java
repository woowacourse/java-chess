package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Step> steps;

    public Path(List<Step> steps) {
        this.steps = steps;
    }

    //TODO 정팩매 이름 찾아
    public static Path of(List<Direction> directions, List<SquareState> squareStates) {
        validateStepSize(directions, squareStates);
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < directions.size(); i++) {
            steps.add(new Step(directions.get(i), squareStates.get(i)));
        }
        return new Path(steps);
    }

    private static void validateStepSize(List<Direction> directions, List<SquareState> squareStates) {
        if (directions.size() != squareStates.size()) {
            throw new IllegalArgumentException("방향의 개수와 상태의 개수가 다릅니다.");
        }
    }


    public boolean isSizeOf(int size) {
        return steps.size() == size;
    }

    //TODO getter 삭제
    //TODO 네이밍 고민
    public boolean categoryNumOf(int categorySize) {
        return steps.stream()
                .map(Step::getDirection)
                .distinct()
                .count() == categorySize;
    }

    public boolean containsDiagonal() {
        return steps.stream()
                .anyMatch(Step::isDiagonal);
    }

    public boolean containsOrthogonal() {
        return steps.stream()
                .anyMatch(Step::isOrthogonal);
    }

    //TODO 네이밍 고민
    public boolean hasPiecePathExcludedTarget() {
        return !isEmptyPathExcludedTarget();
    }

    //TODO targetStep으로 빼기
    private boolean isEmptyPathExcludedTarget() {
        return steps.subList(0, steps.size() - 1)
                .stream()
                .allMatch(Step::isEmpty);
    }

    //TODO 중간에 기물이 위치해있다는 이름으로 변경
    //TODO 마지막 위치는 확인 안하도록 변경
    public boolean isAllEmpty() {
        return steps.stream()
                .allMatch(Step::isEmpty);
    }

    //TODO 목적지를 확인한다는 느낌으로 네이밍 변경
    public boolean canReach() {
        Step lastStep = steps.get(steps.size() - 1);
        return lastStep.isEmpty() || lastStep.isEnemy();
    }

    public boolean isUpside() {
        return steps.stream()
                .allMatch((Step::isUpside));
    }

    public boolean isTargetHasEnemy() {
        return steps.get(steps.size() - 1).isEnemy();
    }

    public boolean isDownside() {
        return steps.stream()
                .allMatch((Step::isDownside));
    }
}
