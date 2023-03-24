package chess.domain.piece.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.ColorCompareResult;
import chess.domain.piece.PieceType;
import chess.domain.piece.exception.IllegalPieceMoveException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class EmptyStateTest {

    @Test
    void 움직일_수_있는_여부를_물어보면_예외가_발생한다() {
        //given
        EmptyState emptyState = EmptyState.getInstance();

        //when
        //then
        assertThatThrownBy(() -> emptyState.canMove(1, 1, ColorCompareResult.EMPTY))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @Test
    void 다음_상태를_가져오려면_예외가_발생한다() {
        //given
        EmptyState emptyState = EmptyState.getInstance();

        //when
        //then
        assertThatThrownBy(emptyState::getNextState)
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @Test
    void 타입을_가져오면_빈_상태가_반환된다() {
        //given
        EmptyState emptyState = EmptyState.getInstance();

        //when
        //then
        assertThat(emptyState.getType()).isEqualTo(PieceType.EMPTY);
    }
}
