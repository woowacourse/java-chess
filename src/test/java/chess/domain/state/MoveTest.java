package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveTest {

    private State move;

    @BeforeEach
    void setUp() {
        State start = new Start();
        start.receive("start");
        State wait = start.next();
        wait.receive("move a2 a3");
        move = wait.next();
    }

    @DisplayName("이동상태 - 명령을 입력 받을 수 없다.")
    @Test
    void receive_throw_exception() {
        assertThatThrownBy(() -> move.receive("end"))
            .isInstanceOf(UnsupportedCommandException.class);
    }

    @DisplayName("이동상태 - 상대 왕이 죽으면 End, 아니면 Wait로 넘어간다.")
    @Test
    void next() {
        State start = new Start();
        start.receive("start");
        State state = start.next();

        List<String> commands = Arrays.asList(
            "move b1 c3",
            "move a7 a6",
            "move c3 b5",
            "move a6 a5",
            "move b5 c7",
            "move a5 a4",
            "move c7 e8"
        );

        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            state.receive(command);
            state = state.next();
            assertThat(state).isInstanceOf(Move.class);

            state.result();
            state = state.next();

            if (i != commands.size() - 1) {
                assertThat(state).isInstanceOf(Wait.class);
                continue;
            }
            assertThat(state).isInstanceOf(End.class);
        }
    }

    @DisplayName("이동상태 - 결과는 이동한 위치를 가진 보드이다.")
    @Test
    void result() {
        Board expectedBoard = BoardUtil.generateInitialBoard();
        expectedBoard.move(Location.of("a2"), Location.of("a3"), Team.WHITE);

        final List<Piece> pieces = ((Move) move).result();
        assertThat(pieces).isEqualTo(expectedBoard.toList());
    }

    @DisplayName("이동상태 - 결과 타입은 보드이다.")
    @Test
    void resultType() {
        assertThat(move.resultType())
            .isEqualTo(ResultType.BOARD);
    }

    @DisplayName("이동상태 - 게임 실행중이다.")
    @Test
    void isRunning() {
        assertThat(move.isRunning())
            .isTrue();
    }

    @DisplayName("이동상태 - 커맨드 입력이 필요없다.")
    @Test
    void needsParam() {
        assertThat(move.needsParam())
            .isFalse();
    }

    @DisplayName("이동상태 - 실패시 입력대기 상태로 돌아온다.")
    @Test
    void before() {
        assertThat(move.before())
            .isInstanceOf(Wait.class);
    }
}
