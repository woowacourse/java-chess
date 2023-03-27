package chess.domain.board;

import chess.TestPiecesGenerator;
import chess.constant.ExceptionCode;
import chess.dao.InMemoryPieceDao;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.property.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static chess.PositionFixture.A1;
import static chess.PositionFixture.A2;
import static chess.PositionFixture.A7;
import static chess.PositionFixture.A8;
import static chess.PositionFixture.D5;
import static chess.PositionFixture.D7;
import static chess.PositionFixture.D8;
import static chess.PositionFixture.E6;
import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class BoardTest {

    @Test
    @DisplayName("초기 체스판이 정상적으로 생성된다")
    void init_test() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Pawn(A7, BLACK),
                new Rook(A8, BLACK),

                new Pawn(A2, WHITE),
                new Rook(A1, WHITE)
        ));
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());
        final Set<Piece> pieces = board.getExistingPieces();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(A7, BLACK, Pawn.class),
                        tuple(A8, BLACK, Rook.class),

                        tuple(A2, WHITE, Pawn.class),
                        tuple(A1, WHITE, Rook.class)
                );
    }

    @Test
    @DisplayName("말을 원하는 위치로 이동시킨다")
    void move_test() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK)
        ));
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        board.move(D8, D5);

        final Set<Piece> pieces = board.getExistingPieces();
        final Piece expected = new Queen(D5, BLACK);

        assertThat(pieces).containsExactly(expected);
    }

    @Test
    @DisplayName("다른 색 말을 잡는다.")
    void catch_test() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK),
                new Pawn(D5, WHITE)
        ));
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        board.move(D8, D5);
        final Set<Piece> pieces = board.getExistingPieces();
        final Piece expectedMovedQueen = new Queen(D5, BLACK);

        assertSoftly(softly -> {
            softly.assertThat(pieces.size()).isEqualTo(1);
            softly.assertThat(pieces).containsExactly(expectedMovedQueen);
        });
    }

    @Test
    @DisplayName("현재 위치에 말이 없다면, 예외가 발생한다")
    void empty_position_access_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of());
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        assertThatThrownBy(() -> board.move(D8, D5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.PIECE_CAN_NOT_FOUND.name());
    }

    @Test
    @DisplayName("목표 위치로 이동할 수 없다면, 예외가 발생한다")
    void invalid_target_position_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK)
        ));
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        assertThatThrownBy(() -> board.move(D8, E6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.INVALID_DESTINATION.name());
    }

    @Test
    @DisplayName("이동 경로에 말이 있다면, 예외가 발생한다")
    void blocked_moving_path_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK),
                new Pawn(D7, BLACK)
        ));
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        assertThatThrownBy(() -> board.move(D8, D5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.PIECE_MOVING_PATH_BLOCKED.name());
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void catching_same_color_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK),
                new Pawn(D7, BLACK)
        ));
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        assertThatThrownBy(() -> board.move(D8, D7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.TARGET_IS_SAME_COLOR.name());
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("같은 색인지 확인한다")
    void ch_is_same_color_test(final Color color, final boolean expected) {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK)
        ));
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        final boolean actual = board.isSameColor(D8, color);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "검사 색 : {1}, 검사 결과 : {2}")
    @MethodSource("providePiecesAndColorToCheckAndExpected")
    @DisplayName("특정 색상의 왕이 존재하는지 확인한다")
    void king_exist_check_test(final List<Piece> pieces, final Color checkingColor, final boolean expected) {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(pieces);
        final Board board = Board.createWith(piecesGenerator, new InMemoryPieceDao());

        final boolean actual = board.isKingExist(checkingColor);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePiecesAndColorToCheckAndExpected() {
        return Stream.of(
                Arguments.of(List.of(new King(A1, BLACK), new Queen(A2, BLACK)), BLACK, true),
                Arguments.of(List.of(new Queen(A1, BLACK), new King(A2, WHITE)), BLACK, false),
                Arguments.of(List.of(new King(A1, WHITE), new Queen(A2, BLACK)), WHITE, true),
                Arguments.of(List.of(new Queen(A1, WHITE), new King(A2, BLACK)), WHITE, false)
        );
    }
}
