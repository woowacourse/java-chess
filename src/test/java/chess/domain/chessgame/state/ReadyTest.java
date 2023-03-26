package chess.domain.chessgame.state;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.SquareCoordinates.A1;
import static chess.domain.SquareCoordinates.A2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ReadyTest {

    @Test
    void 대기_상태일떄_실행시_예외발생_테스트() {
        Ready ready = new Ready();
        assertThatThrownBy(() -> ready.move(A1, A2)).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(ready::isKingDead).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(ready::close).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 완료된_게임_없이_대기_상태일떄_결과_조회시_예외가_발생한다() {
        Ready ready = new Ready();
        assertThatThrownBy(ready::status).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(ready::getChessBoard).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 대기_상태일때_종료_입력시_종료상태를_반환한다() {
        assertThat(new Ready().end()).isInstanceOf(End.class);
    }

    @Test
    void 대기_상태일때_종료되지_않았음을_반환한다() {
        assertThat(new Ready().isNotEnd()).isTrue();
    }
}