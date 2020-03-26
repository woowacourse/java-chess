package chess.domain.piece;

import java.util.List;

import chess.domain.board.Position;

public interface MoveStrategy {

    List<Position> findMovePath(Position source, Position target, boolean isKill);
    // TODO: 구현체들 중 중복되는 메서드를 추상
    // TODO: 2020/03/25 depth 확인
}
