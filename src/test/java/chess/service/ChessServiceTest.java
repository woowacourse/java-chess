package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.event.InitEvent;
import chess.domain.event.MoveEvent;
import chess.domain.game.NewGame;
import chess.dto.CreateGameDto;
import chess.dto.GameCountDto;
import chess.dto.GameDto;
import chess.dto.GameResultDto;
import chess.dto.SearchResultDto;
import chess.service.fixture.EventDaoStub;
import chess.service.fixture.GameDaoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ChessServiceTest {

    private ChessService service;

    @BeforeEach
    void setup() {
        service = new ChessService(new GameDaoStub(), new EventDaoStub());
    }

    @Test
    void countGames_메서드는_전체_게임_수와_실행_중인_게임_수를_담은_데이터를_반환한다() {
        GameCountDto actual = service.countGames();
        GameCountDto expected = new GameCountDto(3, 2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void initGame_메서드는_새로운_게임을_DB에_저장하고_생성된_게임ID가_담긴_데이터를_반환한다() {
        CreateGameDto actual = service.initGame();
        CreateGameDto expected = new CreateGameDto(4);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void searchGame_메서드는_gameId에_해당되는_게임이_있다면_true가_담긴_데이터를_반환한다() {
        SearchResultDto actual = service.searchGame(1);
        SearchResultDto expected = new SearchResultDto(1, true);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void searchGame_메서드는_gameId에_해당되는_게임이_없다면_false가_담긴_데이터를_반환한다() {
        SearchResultDto actual = service.searchGame(99999);
        SearchResultDto expected = new SearchResultDto(99999, false);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findGame_메서드는_현재_게임의_상태와_체스말_정보를_반환한다() {
        GameDto actual = service.findGame(1);

        GameDto expected = new NewGame().play(new InitEvent())
                .play(new MoveEvent("e2 e4"))
                .play(new MoveEvent("d7 d5"))
                .play(new MoveEvent("f1 b5"))
                .toDtoOf(1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findGame_메서드는_존재하지_않는_게임인_경우_예외를_발생시킨다() {
        assertThatThrownBy(() -> service.findGame(999999))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("아직 게임이 시작되지 않았습니다.");
    }

    @Test
    void playGame_메서드는_이동_명령에_따라_이동시킨_후_그_결과를_반환한다() {
        GameDto actual = service.playGame(1, new MoveEvent("a7 a5"));

        GameDto expected = new NewGame().play(new InitEvent())
                .play(new MoveEvent("e2 e4"))
                .play(new MoveEvent("d7 d5"))
                .play(new MoveEvent("f1 b5"))
                .play(new MoveEvent("a7 a5"))
                .toDtoOf(1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void playGame_메서드는_이동_명령에_따라_이동시키며_게임이_종료된_경우_OVER로_상태를_변경한다() {
        service.playGame(2, new MoveEvent("b5 e8"));

        GameCountDto actual = service.countGames();
        GameCountDto expected = new GameCountDto(3, 2 - 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void playGame_메서드는_존재하지_않는_게임인_경우_예외를_발생시킨다() {
        assertThatThrownBy(() -> service.findGame(999999))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("아직 게임이 시작되지 않았습니다.");
    }

    @Test
    void findGameResult_메서드는_종료된_게임의_승자_및_점수_정보를_계산하여_반환한다() {
        GameResultDto actual = service.findGameResult(3);

        GameResultDto expected = new GameResultDto(3, new NewGame()
                .play(new InitEvent())
                .play(new MoveEvent("e2 e4"))
                .play(new MoveEvent("d7 d5"))
                .play(new MoveEvent("f1 b5"))
                .play(new MoveEvent("a7 a5"))
                .play(new MoveEvent("b5 e8")));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findGameResult_메서드는_게임이_종료되지_않은_경우_예외_발생() {
        assertThatThrownBy(() -> service.findGameResult(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아직 게임 결과가 산출되지 않았습니다.");
    }
}