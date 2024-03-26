package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.dao.DaoTest;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProgressTest implements DaoTest {
    @DisplayName("Progress는 command로 \"start\"를 받으면 예외가 발생한다.")
    @Test
    void playWithCommandStart() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when, then
        assertThatThrownBy(() -> progress.play(List.of("start")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Progress는 command로 \"move\"를 받으면 Progress를 반환한다.")
    @Test
    void playWithCommandMove() {
        // given
        ChessBoard chessBoard = new ChessBoard();
        Progress progress = new Progress(chessBoard);

        // when
        GameState result = progress.play(List.of("move", "b2", "b3"));

        // then
        assertThat(result).isInstanceOf(Progress.class);
    }

    @DisplayName("Progress는 command로 \"end\"를 받으면 End를 반환한다.")
    @Test
    void playWithCommandEnd() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when
        GameState result = progress.play(List.of("end"));

        // then
        assertThat(result).isInstanceOf(End.class);
    }

    /*
     * 초기 체스판 상태
     * RNBQKBNR  8 (rank 8)
     * PPPPPPPP  7
     * ...n....  6
     * abcdefgh
     */
    @DisplayName("한쪽 팀의 King이 잡히면 End를 반환한다")
    @Test
    void playWithKingCaptured() {
        // given
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.getChessBoard().put(Position.of(File.D, Rank.SIX), new Knight(Color.WHITE));
        Progress progress = new Progress(chessBoard);

        // when
        GameState result = progress.play(List.of("move", "d6", "e8"));

        // then
        assertThat(result).isInstanceOf(End.class);
    }

    @DisplayName("현재 점수를 통해 승리한 팀을 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {"ONE,BLACK", "EIGHT,WHITE", "FIVE,NONE"})
    void getWinnerColor(Rank removeRank, Color expectedWinnerColor) {
        // given
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.getChessBoard().remove(Position.of(File.B, removeRank));
        Progress progress = new Progress(chessBoard);

        // when
        Color result = progress.getWinnerColor();

        // then
        assertThat(result).isEqualTo(expectedWinnerColor);
    }

    @DisplayName("Progress는 command로 적절하지 않은 입력을 받으면 예외가 발생한다.")
    @Test
    void playWithCommandInvalidValue() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when, then
        assertThatThrownBy(() -> progress.play(List.of("ash", "ella")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Progress는 종료되지 않은 상태이다.")
    @Test
    void isEnd() {
        // given
        Progress progress = new Progress(new ChessBoard());

        // when
        boolean result = progress.isEnd();

        // then
        assertThat(result).isFalse();
    }
}
