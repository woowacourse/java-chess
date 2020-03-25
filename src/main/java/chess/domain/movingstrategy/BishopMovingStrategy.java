package chess.domain.movingstrategy;

import chess.domain.position.Position;

import java.util.function.Predicate;

public class BishopMovingStrategy implements Predicate<Variables> {
    @Override
    public boolean test(Variables variables) {
        // from 에서 to 로 갈 수 있으면 true return
        Position fromPosition = variables.getFromPosition();
        Position toPosition = variables.getToPosition();

        // Todo : 요기차례

        return true; // Todo 불린 리턴 무조건 트루인 상태
    }
}
