package chess.controller.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.command.*;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.piece.Pieces;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.service.ChessGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {

    private final ChessGameService chessGameService = new ChessGameService(new ChessGame(1L, new Board(new Pieces()), Turn.WHITE));

    @Test
    @DisplayName("게임 플레이 상태에서 시작 시 새로운 플레이 상태로 전이된다.")
    void start() {
        // given
        Play play = new Play(chessGameService);

        // when
        CommandStatus newPlay = play.start();

        // then
        assertThat(newPlay).isInstanceOf(Play.class);
        assertThat(newPlay).isNotEqualTo(play);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 이동 시 말이 이동한 상태 & 턴이 넘어간 플레이 상태로 전이된다.")
    void move() {
        // given
        Board board = new Board(new Pieces());
        Play play = new Play(chessGameService);
        Position sourcePosition = new Position(File.A, Rank.TWO);
        Position targetPosition = new Position(File.A, Rank.FOUR);

        // when
        CommandStatus newPlay = play.move(sourcePosition, targetPosition);

        // then
        assertThat(newPlay).isInstanceOf(Play.class);
        assertThat(newPlay).isNotEqualTo(play);
        assertThat(newPlay.getTurnDisplayName()).isEqualTo("black");
    }

    @Test
    @DisplayName("게임 플레이 상태에서 종료 시 종료 상태로 전이된다.")
    void end() {
        // given
        Play play = new Play(chessGameService);

        // when, then
        assertThat(play.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 status 시 게임 출력 상태로 전이된다.")
    void printGameResult() {
        // given
        Play play = new Play(chessGameService);

        // when, then
        assertThat(play.printGameResult()).isInstanceOf(PrintGameResult.class);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 기물들을 가져올 수 있다.")
    void getPieces() {
        // given
        Play play = new Play(chessGameService);
        // when, then
        Assertions.assertDoesNotThrow(() -> play.getPieces());
    }

    @Test
    @DisplayName("게임 플레이 상태에서 턴 이름을 가져올 수 있다.")
    void getTurnDisplayName() {
        // given
        Play play = new Play(chessGameService);

        // when, then
        assertThat(play.getTurnDisplayName()).isEqualTo("white");
    }

    @Test
    @DisplayName("게임 플레이 출력 상태에서 진영별 점수를 가져올 수 있다.")
    void getScoreBySide() {
        // given
        Play play = new Play(chessGameService);

        // when, then
        Assertions.assertDoesNotThrow(() -> play.getScoreBySide());
    }

    @Test
    @DisplayName("게임 플레이 출력 상태에서 진영별 결과를 가져올 수 있다.")
    void getGameResultBySide() {
        // given
        Play play = new Play(chessGameService);

        // when, then
        Assertions.assertDoesNotThrow(() -> play.getGameResultBySide());
    }

    @Test
    @DisplayName("플레이 상태에서 이전 게임으로 재시작할 시 예외를 던진다.")
    void restart() {
        // given
        Play play = new Play(chessGameService);
        Long gameId = 1L;

        // when, then
        assertThatThrownBy(() -> play.restart(gameId))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 플레이 상태에서는 이전 게임으로 재시작할 수 없습니다.");
    }
}
