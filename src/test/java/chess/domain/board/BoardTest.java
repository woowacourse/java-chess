package chess.domain.board;

import chess.TestPiecesGenerator;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.property.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

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
        final Board board = Board.createBoardWith(piecesGenerator);
        final List<Piece> pieces = board.getExistingPieces();

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
        final Board board = Board.createBoardWith(piecesGenerator);

        board.move(D8, D5);

        final List<Piece> pieces = board.getExistingPieces();
        final Piece queen = pieces.get(0);

        assertThat(queen.getPosition()).isEqualTo(D5);
    }

    @Test
    @DisplayName("다른 색 말을 잡는다.")
    void catch_test() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK),
                new Pawn(D5, WHITE)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        board.move(D8, D5);
        final List<Piece> pieces = board.getExistingPieces();
        final Piece queen = pieces.get(0);

        assertSoftly(softly -> {
            softly.assertThat(pieces.size()).isEqualTo(1);
            softly.assertThat(queen).isInstanceOf(Queen.class);
            softly.assertThat(queen.getPosition()).isEqualTo(D5);
        });
    }

    @Test
    @DisplayName("현재 위치에 말이 없다면, 예외가 발생한다")
    void empty_position_access_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of());
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(D8, D5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("목표 위치로 이동할 수 없다면, 예외가 발생한다")
    void invalid_target_position_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(D8, E6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("이동 경로에 말이 있다면, 예외가 발생한다")
    void blocked_moving_path_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK),
                new Pawn(D7, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(D8, D5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 있습니다.");
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void catching_same_color_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK),
                new Pawn(D7, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(D8, D7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("같은 색인지 확인한다")
    void ch_is_same_color_test(final Color color, final boolean expected) {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(D8, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        final boolean actual = board.isSameColor(D8, color);

        assertThat(actual).isEqualTo(expected);
    }
}
