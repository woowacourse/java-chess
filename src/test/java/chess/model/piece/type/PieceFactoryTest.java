package chess.model.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceFactoryTest {

    @ParameterizedTest(name = "create()는 {0}을 건네주면 {1}의 타입의 기물을 반환한다")
    @DisplayName("create() 성공 테스트")
    @CsvSource(value = {
            "chess.model.piece.type.Bishop:chess.model.piece.type.Bishop",
            "chess.model.piece.type.Pawn:chess.model.piece.type.InitialPawn",
            "chess.model.piece.type.King:chess.model.piece.type.King",
            "chess.model.piece.type.Knight:chess.model.piece.type.Knight",
            "chess.model.piece.type.Queen:chess.model.piece.type.Queen",
            "chess.model.piece.type.Rook:chess.model.piece.type.Rook",
    }, delimiter = ':')
    void create_givenPieceType_thenReturnPieceInstance(
            final Class<? extends Piece> pieceType,
            final Class<? extends Piece> expected
    ) {
        // when
        final Piece actual = PieceFactory.create(pieceType, Camp.BLACK);

        // then
        assertThat(actual)
                .isInstanceOf(Piece.class)
                .isExactlyInstanceOf(expected);
    }

    @Test
    @DisplayName("create()는 생성할 수 없는 기물 Empty 클래스를 전달하면 예외가 발생한다")
    void create_givenEmptyClass_thenFail() {
        // when, then
        assertThatThrownBy(() -> PieceFactory.create(Empty.class, Camp.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("존재하지 않는 기물입니다.");
    }
}
