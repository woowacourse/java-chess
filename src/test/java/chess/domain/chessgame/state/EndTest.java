package chess.domain.chessgame.state;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.SquareCoordinates.A1;
import static chess.domain.SquareCoordinates.A2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EndTest {

    @Test
    void 종료_상태일떄_실행시_예외발생_테스트() {
        End end = new End();
        assertThatThrownBy(end::start).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> end.move(A1, A2)).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(end::isKingDead).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(end::close).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(end::status).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(end::getChessBoard).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 종료_상태일때_종료_입력시_예외가_발생하지_않는다() {
        assertDoesNotThrow(() -> new End().end());
    }
}