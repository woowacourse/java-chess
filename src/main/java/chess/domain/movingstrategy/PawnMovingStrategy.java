package chess.domain.movingstrategy;

import java.util.function.Predicate;

public class PawnMovingStrategy implements Predicate<Variables> {
    @Override
    public boolean test(Variables variables) {
        // from 에서 to 로 갈 수 있으면 true return
        return false;
    }
}
