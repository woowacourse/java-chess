package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.generator.InitBoardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("체스 보드를 생성한다.")
    @Test
    void 체스보드를_생성한다() {
        assertDoesNotThrow(() -> new ChessBoard(new InitBoardGenerator()));
    }

    @DisplayName("잘못된 위치 정보인 경우 예외를 던진다.")
    @Test
    void 잘못된_위치_정보인_경우_예외를_던진다() {
        ChessBoard chessBoard = BoardFixtures.generateInitChessBoard();

        assertThatThrownBy(() -> chessBoard.move("i1", "a1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source 위치의 기물이 존재하지 않으면 예외를 던진다.")
    @Test
    void source_위치의_기물이_존재하지_않으면_예외를_던진다() {
        ChessBoard chessBoard = BoardFixtures.generateEmptyChessBoard();

        assertThatThrownBy(() -> chessBoard.move("b1", "c3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 동일한 경우 예외를 던진다.")
    @Test
    void source와_target이_동일한_경우_예외를_던진다() {
        ChessBoard chessBoard = BoardFixtures.generateInitChessBoard();

        assertThatThrownBy(() -> chessBoard.move("b8", "b8"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source 위치와 target 위치는 같을 수 없습니다.");
    }

    @DisplayName("첫 초기화 상태에서 체스 보드의 진영별 점수를 계산하여 반환한다")
    @Test
    void 첫_초기화_상태에서_체스보드의_점수를_계산한다() {
        ChessBoard chessBoard = BoardFixtures.generateInitChessBoard();

        double blackResult = chessBoard.calculateScore(Color.BLACK);
        double whiteResult = chessBoard.calculateScore(Color.WHITE);

        assertAll(
                () -> assertThat(blackResult).isEqualTo(38.0),
                () -> assertThat(whiteResult).isEqualTo(38.0)
        );
    }

    @DisplayName("세로 줄에 같은 색의 폰이 있는 경우 체스 보드의 진영별 점수를 계산하여 반환한다")
    @Test
    void 세로줄에_같은색의_폰이_있는경우_체스보드의_점수를_계산한다() {
        ChessBoard chessBoard = BoardFixtures.generatePawnChessBoard();

        double blackResult = chessBoard.calculateScore(Color.BLACK);
        double whiteResult = chessBoard.calculateScore(Color.WHITE);

        assertAll(
                () -> assertThat(blackResult).isEqualTo(1.5),
                () -> assertThat(whiteResult).isEqualTo(0)
        );
    }
}
