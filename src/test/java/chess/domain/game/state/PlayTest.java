package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.position.Position;
import chess.domain.boardstrategy.BoardStrategy;
import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.boardstrategy.InjectBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {
    private final ChessGame chessGame = new ChessGame(new InitBoardStrategy());

    private State state;

    @BeforeEach
    void setup() {
        state = new Play(chessGame);
    }

    @Test
    @DisplayName("플레이 중 move 커맨드대로 실행할 수 있다.")
    void execute() {
        state = state.execute(new CommandDto("move a2 a3"));
        State finalState = state;
        assertAll(
                () -> assertThat(finalState)
                        .isInstanceOf(Play.class),
                () -> assertThat(chessGame.getBoard().findByPosition(Position.from("a2")))
                        .isInstanceOf(EmptyPiece.class),
                () -> assertThat(chessGame.getBoard().findByPosition(Position.from("a3")))
                        .isInstanceOf(Pawn.class)
        );
    }

    @Test
    @DisplayName("플레이 중 end 커맨드 실행 시 강제종료 된다.")
    void executeExit() {
        state = state.execute(new CommandDto("end"));
        assertThat(state)
                .isInstanceOf(ExitFinished.class);
    }

    @Test
    @DisplayName("잘못된 커맨드 실행 시 예외처리 된다.")
    void executeError() {
        assertThatThrownBy(() -> state.execute(new CommandDto("start")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("해당 상태가 Play 인지 확인할 수 있다.")
    void isPlay() {
        assertThat(state.isPlay())
                .isTrue();
    }

    @Test
    @DisplayName("킹이 잡혔을 시 Result 상태로 이동한다.")
    void goEnd() {
        String boardString = ""
                + "........ "
                + "........ "
                + "........ "
                + "........ "
                + ".......K "
                + "........ "
                + "........ "
                + "R......q";

        BoardStrategy boardStrategy = new InjectBoardStrategy(boardString);
        State state = new Play(new ChessGame(boardStrategy));

        state = state.execute(new CommandDto("move h1 h4"));
        assertThat(state)
                .isInstanceOf(Result.class);
    }

    @Test
    @DisplayName("isRun() 실행 시 true를 리턴한다")
    void isRun() {
        assertThat(state.isRun())
                .isTrue();
    }

    @Test
    @DisplayName("해당 상태가 Status 가 아님을 확인할 수 있다.")
    void isStatus() {
        assertThat(state.isStatus())
                .isFalse();
    }
}
