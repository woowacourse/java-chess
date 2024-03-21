package chess.domain.position;

import java.util.Arrays;

public enum Direction {
    //TODO: 방향의 가중치를 이용한 8방향 도메인으로 변경 고민해보기
    //TODO: 만약 8방향으로 결정했다면 포지션 두개를 받아서 방향을 정하는 로직 누구에게(Direction? Position) 줄 지 생각해보기
    UP(true),
    DOWN(false);

    private final boolean destinationIsAbove;

    Direction(boolean destinationHigher) {
        this.destinationIsAbove = destinationHigher;
    }

    public static Direction from(boolean destinationIsAbove) {
        return Arrays.stream(values())
                .filter(direction -> direction.destinationIsAbove == destinationIsAbove)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("방향을 찾지 못했습니다"));
    }
}
