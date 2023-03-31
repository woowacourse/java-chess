package chess.controller.mapper;

import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PieceMapperTest {

    @Test
    void 체스말_타입을_전달하면_이에_맞는_객체를_반환한다() {
        PieceMapper pieceMapper = PieceMapper.from(PieceType.PAWN);

        assertThat(pieceMapper).isEqualTo(PieceMapper.PAWN);
    }

    @ParameterizedTest
    @CsvSource(value = {"p:PAWN", "P:PAWN", ".:EMPTY", "Q:QUEEN"}, delimiter = ':')
    void 체스_말에_대한_메세지를_입력하면_이에_맞는_객체를_반환한다(String pieceView, PieceMapper expected) {
        PieceMapper pieceMapper = PieceMapper.from(pieceView);

        assertThat(pieceMapper).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"p1", " ", "a"})
    void 체스_말에_대한_메세지를_잘못_입력하면_예외가_발생한다(String pieceView) {
        assertThatThrownBy(() -> PieceMapper.from(pieceView))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 말을 입력했습니다.");
    }
}
