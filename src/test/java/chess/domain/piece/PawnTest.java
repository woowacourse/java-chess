package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.NO_COLOR;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    private Map<Position, Piece> BOARD_MAP = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        BOARD_MAP = EmptyBoardGenerator.create();
    }

    @DisplayName("흰색 폰 이동 가능 - 단순 전진")
    @ParameterizedTest(name = "(2,2)에서 ({0},{1})로 이동 가능")
    @CsvSource({"2,3", "2,4"})
    void canMove_WhitePawn_Forward(int file, int rank) {
        Piece pawn = new Pawn(WHITE);
        Position source = Position.of(2, 2);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, pawn);

        assertThat(pawn.canMove(source, target, BOARD_MAP)).isTrue();
    }

    @DisplayName("검정색 폰 이동 가능 - 단순 전진")
    @ParameterizedTest(name = "(7,7)에서 ({0},{1})로 이동 가능")
    @CsvSource({"7,6", "7,5"})
    void canMove_BlackPawn_Forward(int file, int rank) {
        Piece pawn = new Pawn(BLACK);
        Position source = Position.of(7, 7);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, pawn);

        assertThat(pawn.canMove(source, target, BOARD_MAP)).isTrue();
    }


    @Test
    @DisplayName("폰 이동 가능 - 대각선으로 잡기")
    void canMove_Pawn_Capture() {
        Piece whitePawn = new Pawn(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        Position source = Position.of(4, 4);
        Position target = Position.of(5, 5);

        BOARD_MAP.put(source, whitePawn);
        BOARD_MAP.put(target, blackPawn);

        assertAll(
            () -> assertThat(whitePawn.canMove(source, target, BOARD_MAP)).isTrue(),
            () -> assertThat(blackPawn.canMove(target, source, BOARD_MAP)).isTrue()
        );
    }

    @Test
    @DisplayName("흰색 폰 이동 불가 - 대각선에 말이 없는데 대각선 이동")
    void cannotMove_WhitePawn_NoPieceToCapture() {
        Piece pawn = new Pawn(WHITE);
        Piece noPiece = new NoPiece(NO_COLOR);
        Position source = Position.of(4, 4);
        Position target = Position.of(5, 5);

        BOARD_MAP.put(source, pawn);
        BOARD_MAP.put(target, noPiece);

        assertThat(pawn.canMove(source, target, BOARD_MAP)).isFalse();
    }

    @Test
    @DisplayName("검정색 폰 이동 불가 - 대각선에 말이 없는데 대각선 이동")
    void cannotMove_BlackPawn_NoPieceToCapture() {
        Piece pawn = new Pawn(BLACK);
        Piece noPiece = new NoPiece(NO_COLOR);
        Position source = Position.of(4, 4);
        Position target = Position.of(3, 3);

        BOARD_MAP.put(source, pawn);
        BOARD_MAP.put(target, noPiece);

        assertThat(pawn.canMove(source, target, BOARD_MAP)).isFalse();
    }

    @Test
    @DisplayName("폰 이동 불가 - 전진으로 잡기 불가")
    void cannotMove_WhitePawn_MoveForwardToCapture() {
        Piece whitePawn = new Pawn(WHITE);
        Piece blackPawn = new Pawn(BLACK);
        Position source = Position.of(4, 4);
        Position target = Position.of(4, 5);

        BOARD_MAP.put(source, whitePawn);
        BOARD_MAP.put(target, blackPawn);

        assertAll(
            () -> assertThat(whitePawn.canMove(source, target, BOARD_MAP)).isFalse(),
            () -> assertThat(blackPawn.canMove(target, source, BOARD_MAP)).isFalse()
        );
    }

    @Test
    @DisplayName("흰색 폰 이동 불가 - 뒤로")
    void cannotMove_WhitePawn_Backward() {
        Piece pawn = new Pawn(WHITE);
        Position source = Position.of(4, 4);
        Position target = Position.of(4, 3);

        BOARD_MAP.put(source, pawn);

        assertThat(pawn.canMove(source, target, BOARD_MAP)).isFalse();
    }

    @Test
    @DisplayName("검정색 폰 이동 불가 - 뒤로")
    void cannotMove_BlackPawn_Backward() {
        Piece pawn = new Pawn(BLACK);
        Position source = Position.of(4, 4);
        Position target = Position.of(4, 5);

        BOARD_MAP.put(source, pawn);

        assertThat(pawn.canMove(source, target, BOARD_MAP)).isFalse();
    }
}
