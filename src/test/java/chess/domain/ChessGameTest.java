package chess.domain;

import chess.TestPiecesGenerator;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.Rank.FOUR;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("턴이 바뀌었는지 확인한다")
    void change_turn_test() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                new Pawn(Position.of(A, TWO), WHITE)
        )));

        chessGame.move(Position.of(A, TWO), Position.of(A, FOUR));

        assertThat(chessGame).extracting("currentTurnColor")
                .isEqualTo(BLACK);
    }

    @Test
    @DisplayName("입력 받은 현재 위치 말 색상이 이동할 차례가 아니면, 예외를 던진다.")
    void invalid_turn_color_moving_throw_exception() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                new Pawn(Position.of(A, TWO), WHITE),
                new Pawn(Position.of(A, SEVEN), BLACK)
        )));

        assertThatThrownBy(() -> chessGame.move(Position.of(A, SEVEN), Position.of(A, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 색의 말을 이동시킬 순서가 아닙니다.");
    }
}
