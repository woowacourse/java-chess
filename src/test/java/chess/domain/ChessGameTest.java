package chess.domain;

import chess.TestPiecesFactory;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.A;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("턴이 바뀌었는지 확인한다")
    void change_turn_test() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesFactory(List.of(
                new Pawn(A, TWO, WHITE)
        )));

        chessGame.move(new Position(A, TWO), new Position(A, FOUR));

        assertThat(chessGame).extracting("currentTurnColor")
                .isEqualTo(BLACK);
    }

    @Test
    @DisplayName("입력 받은 현재 위치 말 색상이 이동할 차례가 아니면, 예외를 던진다.")
    void throws_exception_if_current_turn_color_is_different_with_piece_color_in_current_position() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesFactory(List.of(
                new Pawn(A, TWO, WHITE),
                new Pawn(A, SEVEN, BLACK)
        )));

        assertThatThrownBy(() -> chessGame.move(new Position(A, SEVEN), new Position(A, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 색의 말을 이동시킬 순서가 아닙니다.");
    }
}
