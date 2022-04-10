package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;

import chess.controller.FakeGameStateDao;
import chess.controller.FakePieceDao;
import chess.dto.PieceDto;
import chess.dto.ScoreDto;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameServiceTest {

    private final ChessGameService chessGameService =
            new ChessGameService(new FakePieceDao(), new FakeGameStateDao());

    @Test
    @DisplayName("게임을 시작하면 모든 기물들을 초기화 위치에 생성하고 DB에 저장한다.")
    void start() {
        chessGameService.start();
        //actual
        final Map<String, Object> allPiecesByPosition = chessGameService.getPieces();
        //then
        assertThat(allPiecesByPosition).hasSize(32);
        chessGameService.end();
    }

    @Test
    @DisplayName("진행중인 게임이 있는데 게임을 시작하려고 하면 예외를 발생시킨다.")
    void startException() {
        //given
        chessGameService.start();
        //when, then
        assertThatThrownBy(chessGameService::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 진행 중인 게임이 있습니다.");
        chessGameService.end();
    }

    @Test
    @DisplayName("점수를 반환한다.")
    void getScore() {
        //given
        chessGameService.start();
        //when
        final ScoreDto score = chessGameService.getScore();
        //actual
        assertThat(score).isEqualTo(new ScoreDto(38, 38));
        chessGameService.end();
    }

    @Test
    @DisplayName("게임을 종료한다. 게임이 종료되면 DB의 모든 데이터가 삭제된다.")
    void end() {
        //when
        chessGameService.start();
        //actual
        chessGameService.end();
        final Map<String, Object> actual = chessGameService.getPieces();
        //then
        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("진행 중인 게임이 없는데 게임을 종료하려고 하면 예외를 발생시킨다.")
    void endException() {
        //when, then
        assertThatThrownBy(chessGameService::end)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("진행 중인 게임이 없습니다.");
    }

    @Test
    @DisplayName("기물이 움직인 결과를 DB에 저장한다.")
    void move() {
        //given
        chessGameService.start();
        //when
        final Map<String, Object> actual = chessGameService.move("a2", "a4");
        //then
        assertThat(actual).contains(entry("a4", new PieceDto("WHITE", "Pawn")));
        chessGameService.end();
    }

    @Test
    @DisplayName("기물이 공격 당해 사라진다면, 해당 데이터를 DB에서 삭제한다.")
    void moveAttack() {
        //given
        chessGameService.start();
        chessGameService.move("a2", "a4");
        chessGameService.move("b7", "b5");
        //when
        final Map<String, Object> allPiecesByPosition = chessGameService.move("a4", "b5");
        //actual
        assertThat(allPiecesByPosition).doesNotContain(entry("b5", new PieceDto("BLACK", "Pawn")));
        chessGameService.end();
    }
}
