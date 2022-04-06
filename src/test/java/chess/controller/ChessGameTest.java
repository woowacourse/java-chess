package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;

import chess.dto.PieceDto;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("게임을 시작하면 모든 기물들을 초기화 위치에 생성하고 DB에 저장한다.")
    void start() {
        //given
        final ChessGame chessGame = new ChessGame();
        chessGame.start();
        //actual
        final Map<String, Object> allPiecesByPosition = chessGame.getAllPiecesByPosition();
        //then
        assertThat(allPiecesByPosition).hasSize(32);
    }

    @Test
    @DisplayName("진행중인 게임이 있는데 게임을 시작하려고 하면 예외를 발생시킨다.")
    void startException() {
        //given
        final ChessGame chessGame = new ChessGame();
        chessGame.start();
        //when, then
        assertThatThrownBy(chessGame::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 진행중인 게임이 있습니다.");
    }

    @Test
    @DisplayName("게임을 종료한다. 게임이 종료되면 DB의 모든 데이터가 삭제된다.")
    void end() {
        //when
        final ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.end();
        //actual
        final Map<String, Object> actual = chessGame.getAllPiecesByPosition();
        //then
        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("진행 중인 게임이 없는데 게임을 종료하려고 하면 예외를 발생시킨다.")
    void endException() {
        //given
        final ChessGame chessGame = new ChessGame();
        //when, then
        assertThatThrownBy(chessGame::end)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("진행 중인 게임이 없습니다.");
    }

    @Test
    @DisplayName("기물이 움직인 결과를 DB에 저장한다.")
    void move() {
        //given
        final ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move("a2", "a4");
        //when
        final Map<String, Object> actual = chessGame.getAllPiecesByPosition();
        //then
        assertThat(actual).contains(entry("a4", new PieceDto("WHITE", "Pawn")))
                .doesNotContain(entry("a2", new PieceDto("WHITE", "Pawn")));
    }

    @Test
    @DisplayName("기물이 공격 당해 사라진다면, 해당 데이터를 DB에서 삭제한다.")
    void moveAttack() {
        //given
        final ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move("a2", "a4");
        chessGame.move("b7", "b5");
        chessGame.move("a4", "b5");
        //when
        final Map<String, Object> allPiecesByPosition = chessGame.getAllPiecesByPosition();
        //actual
        assertThat(allPiecesByPosition).doesNotContain(entry("b5", new PieceDto("BLACK", "Pawn")));
    }
}
