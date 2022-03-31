package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("이동경로에 기물이 존재하지 않으면 목표 지점에 이동한다.")
    void move() {
        //given
        final Rook rook = new Rook(TeamColor.BLACK, Position.from("a1"));
        final Rook anotherRook = new Rook(TeamColor.BLACK, Position.from("h1"));
        final Position targetPosition = Position.from("a8");
        final Piece moved = rook.move(Collections.singletonList(anotherRook), targetPosition);
        //when
        final boolean actual = moved.matchesPosition(targetPosition);
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("이동경로에 기물이 존재하면 예외를 발생시킨다.")
    void moveException() {
        //given
        final Rook rook = new Rook(TeamColor.BLACK, Position.from("a1"));
        final List<Piece> pieces = Collections.singletonList(new Knight(TeamColor.BLACK, Position.from("a4")));
        final Position targetPosition = Position.from("a8");
        //when, then
        assertThatThrownBy(() -> rook.move(pieces, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 기물이 존재합니다.");
    }

    @Test
    @DisplayName("목표 지점이 갈 수 없는 곳이면 예외를 발생시킨다.")
    void moveExceptionTarget() {
        //given
        final Rook rook = new Rook(TeamColor.BLACK, Position.from("a1"));
        final Position targetPosition = Position.from("b2");
        //when, then
        assertThatThrownBy(() -> rook.move(new ArrayList<>(), targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }
}