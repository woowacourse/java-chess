package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private final List<Step> steps;

    public Route(List<Direction> directions, List<SquareState> squareStates) {
        validateStepSize(directions, squareStates);
        this.steps = createSteps(directions, squareStates);
    }

    private List<Step> createSteps(List<Direction> directions, List<SquareState> squareStates) {
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < directions.size(); i++) {
            steps.add(new Step(directions.get(i), squareStates.get(i)));
        }
        validatePathDistance(steps);
        return steps;
    }

    public boolean isSizeOf(int size) {
        return steps.size() == size;
    }

    public boolean isDirectionsCount(int count) {
        return steps.stream()
                .map(Step::getDirection)
                .distinct()
                .count() == count;
    }

    public boolean containsDiagonal() {
        return steps.stream()
                .anyMatch(Step::isDiagonal);
    }

    public boolean containsOrthogonal() {
        return steps.stream()
                .anyMatch(Step::isOrthogonal);
    }

    public boolean hasPiecePathExclusive() {
        return !isEmptyPathExclusive();
    }

    private boolean isEmptyPathExclusive() {
        return createPathExclusive()
                .stream()
                .allMatch(Step::isEmpty);
    }

    private List<Step> createPathExclusive() {
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

    public boolean isTargetHasEnemy() {
        return steps.get(findTargetIndex()).isEnemy();
    }

    public boolean isUpside() {
        return steps.stream()
                .allMatch((Step::isUpside));
    }

    public boolean isDownside() {
        return steps.stream()
                .allMatch((Step::isDownside));
    }

    private int findTargetIndex() {
        return steps.size() - 1;
    }

    private static void validateStepSize(List<Direction> directions, List<SquareState> squareStates) {
        if (directions.size() != squareStates.size()) {
            throw new IllegalArgumentException("방향의 개수와 상태의 개수가 다릅니다.");
        }
    }

    private void validatePathDistance(List<Step> steps) {
        if (steps.size() > 7) {
            throw new IllegalArgumentException("경로의 길이는 7칸을 넘을 수 없습니다.");
        }
        if (steps.isEmpty()) {
            throw new IllegalArgumentException("제자리 경로를 생성할 수 없습니다.");
        }
    }
}
