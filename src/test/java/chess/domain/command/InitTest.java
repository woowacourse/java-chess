package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dao.ChessGameDao;
import chess.dao.JdbcChessGameDao;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitTest {

    private final ChessGameDao chessGameDao = JdbcChessGameDao.getInstance();

    @Test
    @DisplayName("초기 상태에서 시작 시 플레이 상태로 전이된다.")
    void start() {
        // given
        Init init = new Init(chessGameDao);

        // when, then
        assertThat(init.start()).isInstanceOf(Play.class);
    }

    @Test
    @DisplayName("초기 상태에서 move 시 예외를 던진다.")
    void move() {
        // given
        Init init = new Init(chessGameDao);
        Position sourcePosition = new Position(File.A, Rank.TWO);
        Position targetPosition = new Position(File.A, Rank.FOUR);

        // when, then
        assertThatThrownBy(() -> init.move(sourcePosition, targetPosition))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 기물을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("초기 상태에서 시작 시 종료 상태로 전이된다.")
    void end() {
        // given
        Init init = new Init(chessGameDao);

        // when, then
        assertThat(init.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("초기 상태에서 보드를 가져올 시 예외를 던진다.")
    void getPieces() {
        // given
        Init init = new Init(chessGameDao);

        // when, then
        assertThatThrownBy(() -> init.getPieces())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("초기 상태에서 턴 이름을 가져올 시 예외를 던진다.")
    void getTurnDisplayName() {
        // given
        Init init = new Init(chessGameDao);

        // when, then
        assertThatThrownBy(() -> init.getTurnDisplayName())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 턴 이름을 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("초기 상태에서 게임 결과를 출력할 시 예외를 던진다.")
    void printGameResult() {
        // given
        Init init = new Init(chessGameDao);

        // when, then
        assertThatThrownBy(() -> init.printGameResult())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 게임 결과를 출력할 수 없습니다.");
    }

    @Test
    @DisplayName("초기 상태에서 점수를 가져올 시 예외를 던진다.")
    void getScoreBySide() {
        // given
        Init init = new Init(chessGameDao);

        // when, then
        assertThatThrownBy(() -> init.getScoreBySide())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 점수를 가져올 수 없습니다.");
    }

    @Test
    @DisplayName("초기 상태에서 게임 결과를 출력할 시 예외를 던진다.")
    void getGameResultBySide() {
        // given
        Init init = new Init(chessGameDao);

        // when, then
        assertThatThrownBy(() -> init.getGameResultBySide())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 초기 상태에서는 결과를 가져올 수 없습니다.");
    }
    
    @Test
    @DisplayName("초기 상태에서 이전 게임이 존재하는지 확인할 수 있다.")
    void isExistPreviousGame() {
        // given
        Init init = new Init(chessGameDao);
        Long gameId = 1L;
        
        // when, then
        assertThat(init.isExistPreviousGame(gameId)).isTrue();
    }
}
