package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.utils.BoardUtil;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StartTest {

    private Start start;

    @BeforeEach
    void setUp() {
        start = new Start();
    }

    @DisplayName("시작상태 - start와 end 커맨드만 가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"start", "end"})
    void receive(String command) {
        assertThatCode(() -> start.receive(command))
            .doesNotThrowAnyException();
    }

    @DisplayName("시작상태 - start와 end 이외의 명령은 불가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"status", "move a1 a2"})
    void receive_unsupported_command(String command) {
        assertThatThrownBy(() -> start.receive(command))
            .isInstanceOf(UnsupportedCommandException.class);
    }

    @DisplayName("시작상태 - start 커맨드가 입력되면 다음은 wait로 넘어간다.")
    @Test
    void next_startCommand() {
        start.receive("start");

        assertThat(start.next())
            .isInstanceOf(Wait.class);
    }

    @DisplayName("시작상태 - end 커맨드가 입력되면 다음은 end로 넘어간다.")
    @Test
    void next_endCommand() {
        start.receive("end");

        assertThat(start.next())
            .isInstanceOf(End.class);
    }

    @DisplayName("시작상태 - 결과는 초기 위치를 가진 보드이다.")
    @Test
    void result() {
        List<Piece> pieces = start.result();
        Board initialBoard = BoardUtil.generateInitialBoard();

        assertThat(pieces).isEqualTo(initialBoard.toList());
    }

    @DisplayName("시작상태 - 결과 타입은 보드이다.")
    @Test
    void resultType() {
        assertThat(start.resultType())
            .isEqualTo(ResultType.BOARD);
    }

    @DisplayName("시작상태 - 게임 실행중이다.")
    @Test
    void isRunning() {
        assertThat(start.isRunning())
            .isTrue();
    }

    @DisplayName("시작상태 - 커맨드 입력이 필요하다.")
    @Test
    void needsParam() {
        assertThat(start.needsParam())
            .isTrue();
    }

    @DisplayName("시작상태 - 실패시 상태를 유지한다.")
    @Test
    void before() {
        assertThat(start.before())
            .isInstanceOf(Start.class);
    }
}