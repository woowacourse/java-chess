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

    public static Path of(List<Direction> directions, List<LocationState> locationStates) {
        validateStepSize(directions, locationStates);
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < directions.size(); i++) {
            steps.add(new Step(directions.get(i), locationStates.get(i)));
        }
        return new Path(steps);
    }

    private static void validateStepSize(List<Direction> directions, List<LocationState> locationStates) {
        if (directions.size() != locationStates.size()) {
            throw new IllegalArgumentException("방향의 개수와 상태의 개수가 다릅니다.");
        }
    }


    public boolean isDistanceOf(int distance) {
        return steps.size() == distance;
    }

    public boolean hasCountOfDirection(int countOfDirection) {
        return steps.stream()
                .map(Step::getDirection)
                .distinct()
                .count() == countOfDirection;
    }

    public boolean containsDiagonalDirection() {
        return steps.stream()
                .anyMatch(Step::isDiagonalDirection);
    }

    public boolean containsOrthogonalDirection() {
        return steps.stream()
                .anyMatch(Step::isOrthogonalDirection);
    }

    public boolean hasPiecePathExcludedTarget() {
        return createPathExcludedTarget()
                .stream()
                .anyMatch(Step::hasPiece);
    }

    private List<Step> createPathExcludedTarget() {
        return steps.subList(0, findTargetIndex());
    }

    public boolean isAllEmpty() {
        return steps.stream()
                .allMatch(Step::isEmpty);
    }

    public boolean isEnemyAtTarget() {
        return steps.get(findTargetIndex()).hasEnemy();
    }

    public boolean isNotAllyAtTarget() {
        Step lastStep = steps.get(findTargetIndex());
        return lastStep.isEmpty() || lastStep.hasEnemy();
    }

    private int findTargetIndex() {
        return steps.size() - 1;
    }

    public boolean isUpside() {
        return steps.stream()
                .allMatch(Step::isUpside);
    }

    public boolean isDownside() {
        return steps.stream()
                .allMatch(Step::isDownside);
    }
}
