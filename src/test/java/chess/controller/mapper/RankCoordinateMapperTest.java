package chess.controller.mapper;

import chess.domain.board.RankCoordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankCoordinateMapperTest {

    @ParameterizedTest
    @CsvSource(value = {"1:ONE", "2:TWO", "8:EIGHT"}, delimiter = ':')
    void 행_메세지를_전달하면_이에_맞는_객체를_반환한다(String rowView, RankCoordinate expect) {
        RankCoordinate rankCoordinate = RankCoordinateMapper.findBy(rowView);

        assertThat(rankCoordinate).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "a", " "})
    void 올바르지_않는_행_메세지를_전달하면_예외가_발생한다(String rowView) {
        assertThatThrownBy(() -> RankCoordinateMapper.findBy(rowView))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 행 번호를 입력해주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1:ONE", "2:TWO", "8:EIGHT"}, delimiter = ':')
    void 행_값을_전달하면_이에_맞는_객체를_반환한다(int rowView, RankCoordinate expect) {
        RankCoordinate rankCoordinate = RankCoordinateMapper.findBy(rowView);

        assertThat(rankCoordinate).isEqualTo(expect);
    }

    @Test
    void 올바르지_않는_행_값을_전달하면_예외가_발생한다() {
        assertThatThrownBy(() -> RankCoordinateMapper.findBy(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 행 번호를 입력해주세요.");
    }
}
