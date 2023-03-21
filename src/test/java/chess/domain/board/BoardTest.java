package chess.domain.board;

import chess.TestPiecesGenerator;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.maker.PiecesGenerator;
import chess.domain.piece.property.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.WHITE;
import static chess.domain.position.File.A;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.Rank.EIGHT;
import static chess.domain.position.Rank.FIVE;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.SEVEN;
import static chess.domain.position.Rank.SIX;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class BoardTest {

    @Test
    @DisplayName("초기 체스판이 정상적으로 생성된다")
    void init_test() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Pawn(Position.of(A, SEVEN), BLACK),
                new Rook(Position.of(A, EIGHT), BLACK),

                new Pawn(Position.of(A, TWO), WHITE),
                new Rook(Position.of(A, ONE), WHITE)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);
        final List<Piece> pieces = board.getExistingPieces();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(Position.of(A, SEVEN), BLACK, Pawn.class),
                        tuple(Position.of(A, EIGHT), BLACK, Rook.class),

                        tuple(Position.of(A, TWO), WHITE, Pawn.class),
                        tuple(Position.of(A, ONE), WHITE, Rook.class)
                );
    }

    @Test
    @DisplayName("말을 원하는 위치로 이동시킨다")
    void move_test() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(Position.of(D, EIGHT), BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        board.move(Position.of(D, EIGHT), Position.of(D, FIVE));

        final List<Piece> pieces = board.getExistingPieces();
        final Piece queen = pieces.get(0);

        assertThat(queen.getPosition()).isEqualTo(Position.of(D, FIVE));
    }

    @Test
    @DisplayName("다른 색 말을 잡는다.")
    void catch_test() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(Position.of(D, EIGHT), BLACK),
                new Pawn(Position.of(D, FIVE), WHITE)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        board.move(Position.of(D, EIGHT), Position.of(D, FIVE));
        final List<Piece> pieces = board.getExistingPieces();
        final Piece queen = pieces.get(0);

        assertSoftly(softly -> {
            softly.assertThat(pieces.size()).isEqualTo(1);
            softly.assertThat(queen).isInstanceOf(Queen.class);
            softly.assertThat(queen.getPosition()).isEqualTo(Position.of(D, FIVE));
        });
    }

    @Test
    @DisplayName("현재 위치에 말이 없다면, 예외가 발생한다")
    void empty_position_access_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of());
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(Position.of(D, EIGHT), Position.of(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("목표 위치로 이동할 수 없다면, 예외가 발생한다")
    void invalid_target_position_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(Position.of(D, EIGHT), BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(Position.of(D, EIGHT), Position.of(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("이동 경로에 말이 있다면, 예외가 발생한다")
    void blocked_moving_path_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(Position.of(D, EIGHT), BLACK),
                new Pawn(Position.of(D, SEVEN), BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(Position.of(D, EIGHT), Position.of(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 있습니다.");
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void catching_same_color_throw_exception() {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(Position.of(D, EIGHT), BLACK),
                new Pawn(Position.of(D, SEVEN), BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        assertThatThrownBy(() -> board.move(Position.of(D, EIGHT), Position.of(D, SEVEN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("같은 색인지 확인한다")
    void ch_is_same_color_test(final Color color, final boolean expected) {
        final PiecesGenerator piecesGenerator = new TestPiecesGenerator(List.of(
                new Queen(Position.of(D, EIGHT), BLACK)
        ));
        final Board board = Board.createBoardWith(piecesGenerator);

        final boolean actual = board.isSameColor(Position.of(D, EIGHT), color);

        assertThat(actual).isEqualTo(expected);
    }
}
