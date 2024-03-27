package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LetteringTest {

    @Test
    void 현재_레터링이_제일_오른쪽에_위치해_있으면_다음_레터링이_없어_예외를_발생시킨다() {
        assertThatThrownBy(() ->
                Lettering.findNextLettering(Lettering.H)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 현재_레터링이_제일_왼쪽에_위치해_있으면_이전_레터링이_없어_예외를_발생시킨다() {
        assertThatThrownBy(() ->
                Lettering.findPreviousLettering(Lettering.A)).isInstanceOf(IllegalArgumentException.class);
    }
}
