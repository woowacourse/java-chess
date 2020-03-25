package chess.domain.movingstrategy;

import java.util.function.Predicate;

public class KingMovingStrategy implements Predicate<Variables> {
    @Override
    public boolean test(Variables variables) {
        // from 에서 to 로 갈 수 있으면 true return
        return true; // Todo 불린 리턴 무조건 트루인 상태
    }
}
