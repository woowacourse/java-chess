package chess.piece;

import static chess.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    //B2에서 킹이 이동가능한 방향
    static Stream<Position> provideKingMovesTo() {
        return Stream.of(A2, C2, B3, B1, A3, A1, C3, C1);
    }

    @DisplayName("킹은 왼쪽 한칸으로 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("provideKingMovesTo")
    void King_canMove_Left(Position target) {
        // given
        King king = new King();

        // when
        boolean canMove = king.canMove(B2, target);

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("킹이 이동할 수 없는 칸이면 false")
    @Test
    void King_cannotMove() {
        King king = new King();

        // when
        boolean canMove = king.canMove(B2, D2);

        // then
        assertThat(canMove).isFalse();
    }

}