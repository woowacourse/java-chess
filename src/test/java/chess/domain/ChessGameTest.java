package chess.domain;

import chess.TestPiecesGenerator;
import chess.constant.ExceptionCode;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.PositionFixture.A2;
import static chess.PositionFixture.A4;
import static chess.PositionFixture.A6;
import static chess.PositionFixture.A7;
import static chess.PositionFixture.B5;
import static chess.PositionFixture.E1;
import static chess.PositionFixture.E8;
import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ChessGameTest {

    @Test
    @DisplayName("턴이 바뀌었는지 확인한다")
    void change_turn_test() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                new Pawn(A2, WHITE)
        )));

        chessGame.move(A2, A4);

        assertThat(chessGame).extracting("currentTurnColor")
                .isEqualTo(BLACK);
    }

    @Test
    @DisplayName("입력 받은 현재 위치 말 색상이 이동할 차례가 아니면, 예외를 던진다.")
    void invalid_turn_color_moving_throw_exception() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                new Pawn(A2, WHITE),
                new Pawn(A7, BLACK)
        )));

        assertThatThrownBy(() -> chessGame.move(A7, A6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.INVALID_TURN.name());
    }

    @Test
    @DisplayName("왕이 잡혔는지 확인한다")
    void king_caught_state_check_test() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                new King(E1, WHITE),
                new King(E8, BLACK),
                new Queen(B5, WHITE)
        )));

        final boolean initialKingCaught = chessGame.isKingCaught();
        chessGame.move(B5, E8);
        final boolean afterMovedKingCaught = chessGame.isKingCaught();

        assertSoftly(softly -> {
            softly.assertThat(initialKingCaught).isFalse();
            softly.assertThat(afterMovedKingCaught).isTrue();
        });
    }
}
