package chess.gamecommand;

import static org.assertj.core.api.Assertions.assertThat;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.Pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayTest {

    @Test
    @DisplayName("게임 플레이 상태에서 시작 시 새로운 플레이 상태로 전이된다.")
    void start() {
        // given
        Play play = new Play(new Board(new Pieces()));

        // when
        CommandStatus newPlay = play.start();

        // then
        assertThat(newPlay).isInstanceOf(Play.class);
        assertThat(newPlay).isNotEqualTo(play);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 이동 시 말이 이동한 상태의 플레이 상태로 전이된다.")
    void move() {
        // given
        Play play = new Play(new Board(new Pieces()));
        Position sourcePosition = new Position(File.A, Rank.TWO);
        Position targetPosition = new Position(File.A, Rank.FOUR);

        // when
        CommandStatus newPlay = play.move(sourcePosition, targetPosition);

        // then
        assertThat(newPlay).isInstanceOf(Play.class);
        assertThat(newPlay).isNotEqualTo(play);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 종료 시 종료 상태로 전이된다.")
    void end() {
        // given
        Play play = new Play(new Board(new Pieces()));

        // when, then
        assertThat(play.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("게임 플레이 상태에서 기물들을 가져올 수 있다.")
    void getPieces() {
        // given
        Play play = new Play(new Board(new Pieces()));

        // when, then
        Assertions.assertDoesNotThrow(() -> play.getPieces());
    }
}
