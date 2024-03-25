package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;

public interface MoveStrategy {

    // TODO: 폰만을 위한 인자인 colorType을 제거할 수 있는 방안 생각해보기
    boolean check(Square source, Square destination, ColorType colorType);
}
