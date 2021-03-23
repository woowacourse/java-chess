package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Board board;
    private Map<Position, Piece> rawBoard;

    @BeforeEach
    void setUp() {
        board = new Board();
        rawBoard = new TreeMap<>(board.unwrap());
    }

    @Test
    @DisplayName("피스 이동 기능")
    void move() {
        board.move(new Position("a", "2"), new Position("a", "3"), Team.WHITE);
        assertThat(board.unwrap().get(new Position("a", "2"))).isInstanceOf(Blank.class);
        assertThat(board.unwrap().get(new Position("a", "3"))).isEqualTo(new Pawn(Team.WHITE));
    }

    @ParameterizedTest
    @DisplayName("룩, 비숍, 퀸, 나이트, 킹 이동이 불가능한 경우")
    @ValueSource(strings = {"a,1,a,7", "c,1,h,7", "d,1,d,8", "d,1,a,4", "b,1,c,2", "e,1,d,1"})
    void checkPath(final String input) {
        final String[] inputs = input.split(",");
        assertThatThrownBy(() -> board.move(new Position(inputs[0], inputs[1]), new Position(inputs[2], inputs[3]), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("폰 이동이 불가능한 경우")
    @ValueSource(strings = {"a,2,a,3", "a,2,a,4", "a,2,b,3"})
    void checkPawnPath(final String input) {
        final Team team = Team.WHITE;
        rawBoard.put(new Position("a", "3"), new Queen(team));
        final String[] inputs = input.split(",");
        assertThatThrownBy(() -> new Board(rawBoard).move(new Position(inputs[0], inputs[1]), new Position(inputs[2], inputs[3]), team))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("킹 한칸만 이동하는 기능")
    void checkKingMove() {
        rawBoard.put(new Position("a", "3"), new King(Team.WHITE));
        assertThatThrownBy(() ->
                new Board(rawBoard).move(new Position("a", "3"), new Position("h", "3"), Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("킹이 잡혔는지 확인하는 기능")
    void checkDieKing() {
        assertThat(board.isKingDead()).isFalse();
        rawBoard.put(new Position("e", "1"), Blank.getInstance());
        assertThat(new Board(rawBoard).isKingDead()).isTrue();
    }

    @Test
    @DisplayName("턴에 맞는 위치를 선택했는지 검증하는 기능")
    void isRightTurn() {
        final Team team = Team.BLACK;
        assertThatThrownBy(() -> board.move(new Position("a", "2"), new Position("a", "3"), team))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 턴에 맞는 말을 움직이세요.");
    }


    @Test
    @DisplayName("빈칸 이동 오류 확인")
    void checkBlankError() {
        assertThatThrownBy(() -> board.move(new Position("a", "3"), new Position("a", "5"), Team.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("비어 있는 칸입니다.");
    }
}