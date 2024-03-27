package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NumberingTest {

    @Test
    void 현재_넘버링이_제일_위쪽에_위치해_있으면_다음_넘버링이_없어_예외를_발생시킨다() {
        assertThatThrownBy(() ->
                Numbering.findNextNumbering(Numbering.EIGHT)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 현재_넘버링이_제일_아래쪽에_위치해_있으면_이전_넘버링이_없어_예외를_발생시킨다() {
        assertThatThrownBy(() ->
                Numbering.findPreviousNumbering(Numbering.ONE)).isInstanceOf(IllegalArgumentException.class);
    }
}
