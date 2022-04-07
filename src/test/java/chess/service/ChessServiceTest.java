package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.FakeBoardDao;
import chess.dao.FakeTurnDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.ChessDto;
import chess.dto.MoveDto;
import chess.dto.StatusDto;
import chess.util.JdbcTestFixture;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    private ChessService chessService;

    @BeforeEach
    void setUp() {
        chessService = new ChessService(new FakeBoardDao(), new FakeTurnDao());
    }

    @Test
    @DisplayName("게임이 종료되면 원래대로 돌아온다.")
    void endGame() {
        ChessDto chessDto = chessService.endGame();
        ChessDto expected = ChessDto.of(new Board(BoardFactory.initialize()));

        assertAll(
                () -> assertThat(chessDto.getGameOver()).isEqualTo("true"),
                () -> assertThat(chessDto.getTurn()).isEqualTo("white"),
                () -> assertThat(chessDto.getBoard()).isEqualTo(expected.getBoard())
        );
    }

    @Test
    @DisplayName("게임이 실행되면 저장된 턴, 보드값을 가져온다.")
    void startGame() {
        ChessDto chessDto = chessService.initializeGame();
        assertAll(
                () -> assertThat(chessDto.getTurn()).isEqualTo("white"),
                () -> assertThat(chessDto.getBoard()).isEqualTo(JdbcTestFixture.getMovedTestBoard())
        );
    }

    @Test
    @DisplayName("점수를 반환한다.")
    void createStatus() {
        StatusDto status = chessService.createStatus();

        assertAll(
                () -> assertThat(status.getWhiteScore()).isEqualTo(38.0),
                () -> assertThat(status.getBlackScore()).isEqualTo(38.0),
                () -> assertThat(status.getWinningTeam()).isEqualTo("black")
        );
    }

    @Test
    @DisplayName("움직인다.")
    void move() {
        ChessDto chessDto = chessService.move(new MoveDto("a4", "a5"));

        Map<String, String> expected = JdbcTestFixture.getMovedTestBoard();
        expected.put("a5", "white_pawn");
        expected.put("a4", "blank");
        assertAll(
                () -> assertThat(chessDto.getTurn()).isEqualTo("black"),
                () -> assertThat(chessDto.getGameOver()).isEqualTo("false"),
                () -> assertThat(chessDto.getBoard()).isEqualTo(expected)
        );
    }

}
