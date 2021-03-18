package chess.domain.piece;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PawnTest {
    @Test
    @DisplayName("이동할 수 있는 좌표가 주어졌을 때, 폰이 움직일 수 있는 칸인지 체크한다.")
    void when_give_position_check_pawn_movable() {
//        ChessBoard chessBoard = new ChessBoard();
//        Pawn pawn = new Pawn("Black");
        Position current = Position.of("a7");
        Position destination = Position.of("a6");
//        assertThat(pawn.isMovable(current, destination, chessBoard)).isTrue();
    }
}
