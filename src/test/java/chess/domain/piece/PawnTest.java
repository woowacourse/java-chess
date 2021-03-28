package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {
    private Pieces initialPieces;

    @BeforeEach
    void setUp() {
        initialPieces = new Pieces(PieceFactory.initialPieces());
    }

    @DisplayName("Pawn 객체 생성 확인")
    @Test
    void 폰_객체_생성() {
        Pawn pawn = new Pawn(Position.of("a7"), Color.BLACK);

        assertThat(pawn.isSamePosition(Position.of("a7"))).isTrue();
        assertThat(pawn.getName()).isEqualTo("P");
    }

    @DisplayName("폰의 이동을 확인한다.")
    @Test
    void 폰_이동() {
        Pawn pawn = new Pawn(Position.of("a7"), Color.BLACK);
        Position target = Position.of("c6");

        pawn.move(target);

        assertThat(pawn.isSamePosition(target)).isTrue();
    }

    @DisplayName("Pawn 이동 규칙을 확인한다. 처음 Pawn을 움직이는 경우 - 2칸 이동")
    @Test
    void pawn_처음으로_이동_2칸() {
        Position source = Position.of("a7");
        Position target = Position.of("a5");
        Piece pawn = initialPieces.findByPosition(source);
        Piece targetPiece = initialPieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        pawn.checkMovable(targetPiece, direction);
    }

    @DisplayName("Pawn 규칙에 따라 처음 Pawn을 움직이는 경우 예외 - 3칸 이동 ")
    @Test
    void pawn_처음으로_이동_3칸_예외() {
        Position source = Position.of("f8");
        Position target = Position.of("g6");
        Piece pawn = initialPieces.findByPosition(source);
        Piece targetPiece = initialPieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        assertThatThrownBy((() ->
                pawn.checkMovable(targetPiece, direction)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pawn 규칙에 따라 이미 움직인 Pawn을 움직이는 경우 예외 - 2칸 이동 ")
    @Test
    void pawn_이미_이동_2칸_예외() {
        Position source = Position.of("a6");
        Position target = Position.of("a4");
        Piece pawn = initialPieces.findByPosition(source);
        Piece targetPiece = initialPieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        assertThatThrownBy((() ->
                pawn.checkMovable(targetPiece, direction)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("검정 말이 흰 말을 공격한다.")
    @Test
    void 검은말이_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of("a7"), Color.BLACK),
                new Pawn(Position.of("b6"), Color.WHITE));
        Pieces pieces = new Pieces(current);
        Position source = Position.of("a7");
        Position target = Position.of("b6");
        Piece pawn = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        pawn.checkMovable(targetPiece, direction);
    }

    @DisplayName("흰 말이 검정 말을 공격한다.")
    @Test
    void 흰말이_상대편_말을_공격한다() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of("a2"), Color.WHITE),
                new Pawn(Position.of("b3"), Color.BLACK));
        Pieces pieces = new Pieces(current);

        Position source = Position.of("a2");
        Position target = Position.of("b3");
        Piece pawn = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        pawn.checkMovable(targetPiece, direction);
    }

    @DisplayName("흰 말이 검정 말을 공격한다. - 말이 없을 경우")
    @Test
    void 흰말이_상대편_말을_공격한다_예외() {
        List<Piece> current = Arrays.asList(
                new Pawn(Position.of("a2"), Color.WHITE));
        Pieces pieces = new Pieces(current);

        Position source = Position.of("a2");
        Position target = Position.of("b3");
        Piece pawn = pieces.findByPosition(source);
        Piece targetPiece = pieces.findByPosition(target);
        Direction direction = Direction.findDirectionByTwoPosition(source, target);

        assertThatThrownBy(() -> pawn.checkMovable(targetPiece, direction))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
