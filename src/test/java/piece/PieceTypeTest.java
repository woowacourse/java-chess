package piece;

import chess.domain.board.PositionFactory;
import chess.domain.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PieceTypeTest {

    @ParameterizedTest
    @DisplayName("필드 변수로 갖고 있는 문자를 입력받으면 enum 클래스(PieceType)를 정상 반환해야 함")
    @ValueSource(strings = {".", "B", "P", "K", "N", "Q", "R", "b", "p", "k", "n", "q", "r"})
    void inputVarStringThenReturnPieceTypeClass(String input) {
        Assertions.assertThat(PieceType.of(input)).isInstanceOf(PieceType.class);
    }

    @Test
    @DisplayName("PieceType 클래스의 of 메서드와 createPiece 메서드로 올바른 체스말을 만들어야 함")
    void inputPieceNameThenReturnPiece() {
        Piece piece = PieceType.of(".").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(EmptyPiece.class);

        piece = PieceType.of("B").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Bishop.class);

        piece = PieceType.of("P").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(BlackPawn.class);

        piece = PieceType.of("K").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(King.class);

        piece = PieceType.of("N").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Knight.class);

        piece = PieceType.of("Q").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Queen.class);

        piece = PieceType.of("R").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Rook.class);

        piece = PieceType.of("b").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Bishop.class);

        piece = PieceType.of("p").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(WhitePawn.class);

        piece = PieceType.of("k").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(King.class);

        piece = PieceType.of("n").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Knight.class);

        piece = PieceType.of("q").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Queen.class);

        piece = PieceType.of("r").createPiece(PositionFactory.of("a1"));
        Assertions.assertThat(piece).isInstanceOf(Rook.class);
    }
}
