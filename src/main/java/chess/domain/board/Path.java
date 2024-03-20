package chess.domain.board;

import java.util.List;

public class Path {
    private final List<Step> steps;

    public Path(List<Step> steps) {
        this.steps = steps;
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

    public boolean isAllEmpty() {
        return steps.stream()
                .allMatch(Step::isEmpty);
    }

    public boolean canReach() {
        Step lastStep = steps.get(steps.size() - 1);
        return lastStep.isEmpty() || lastStep.isEnemy();
    }
}
