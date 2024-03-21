package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Step> steps;

    public Path(List<Step> steps) {
        validatePathDistance(steps);
        this.steps = steps;
    }

    private void validatePathDistance(List<Step> steps) {
        if (steps.size() > 7) {
            throw new IllegalArgumentException("경로의 길이는 7칸을 넘을 수 없습니다.");
        }
        if (steps.isEmpty()) {
            throw new IllegalArgumentException("제자리 경로를 생성할 수 없습니다.");
        }
    }

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

    public boolean hasPiecePathExcludedTarget() {
        return !isEmptyPathExcludedTarget();
    }

    private boolean isEmptyPathExcludedTarget() {
        return createPathExcludedTarget()
                .stream()
                .allMatch(Step::isEmpty);
    }

    private List<Step> createPathExcludedTarget() {
        return steps.subList(0, findTargetIndex());
    }


    public boolean isAllEmpty() {
        return steps.stream()
                .allMatch(Step::isEmpty);
    }

    public boolean hasNoAllyAtTarget() {
        Step lastStep = steps.get(findTargetIndex());
        return lastStep.isEmpty() || lastStep.isEnemy();
    }

    public boolean isUpside() {
        return steps.stream()
                .allMatch((Step::isUpside));
    }

    public boolean isTargetHasEnemy() {
        return steps.get(findTargetIndex()).isEnemy();
    }

    public boolean isDownside() {
        return steps.stream()
                .allMatch((Step::isDownside));
    }

    private int findTargetIndex() {
        return steps.size() - 1;
    }
}
