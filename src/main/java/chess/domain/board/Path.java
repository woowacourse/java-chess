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

    //TODO 네이밍 고민
    public boolean hasPiecePathExcludedTarget() {
        return !isEmptyPathExcludedTarget();
    }

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
}
