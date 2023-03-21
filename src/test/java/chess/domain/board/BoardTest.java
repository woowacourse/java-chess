package chess.domain.board;

import chess.TestPiecesFactory;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.maker.PiecesFactory;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.*;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class BoardTest {

    @Test
    @DisplayName("초기 체스판이 정상적으로 생성된다")
    void succeeds_in_creation() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Pawn(A, SEVEN, BLACK),
                new Rook(A, EIGHT, BLACK),

                new Pawn(A, TWO, WHITE),
                new Rook(A, ONE, WHITE)
        ));
        final Board board = Board.createBoardWith(piecesFactory);
        final List<Piece> pieces = board.getPieces();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(new Position(A, SEVEN), BLACK, Pawn.class),
                        tuple(new Position(A, EIGHT), BLACK, Rook.class),

                        tuple(new Position(A, TWO), WHITE, Pawn.class),
                        tuple(new Position(A, ONE), WHITE, Rook.class)
                );
    }

    @Test
    @DisplayName("말을 원하는 위치로 이동시킨다")
    void move_test() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        board.move(new Position(D, EIGHT), new Position(D, FIVE));

        final List<Piece> pieces = board.getPieces();
        final Piece queen = pieces.get(0);

        assertThat(queen.getPosition()).isEqualTo(new Position(D, FIVE));
    }

    @Test
    @DisplayName("다른 색 말을 잡는다.")
    void catch_test() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, FIVE, WHITE)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        board.move(new Position(D, EIGHT), new Position(D, FIVE));
        final List<Piece> pieces = board.getPieces();
        final Piece queen = pieces.get(0);

        assertSoftly(softly -> {
            softly.assertThat(pieces.size()).isEqualTo(1);
            softly.assertThat(queen).isInstanceOf(Queen.class);
            softly.assertThat(queen.getPosition()).isEqualTo(new Position(D, FIVE));
        });
    }

    @Test
    @DisplayName("현재 위치에 말이 없다면, 예외가 발생한다")
    void can_not_find_piece_in_current_position() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of());
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("목표 위치로 이동할 수 없다면, 예외가 발생한다")
    void throws_exception_if_can_not_move_to_target_position() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("이동 경로에 말이 있다면, 예외가 발생한다")
    void throws_exception_if_there_is_piece_in_passing_path() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, SEVEN, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 있습니다.");
    }

    @Test
    @DisplayName("목표 위치에 같은 색 말이 있다면, 예외가 발생한다")
    void throws_exception_if_there_is_same_color_piece_in_target_position() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, SEVEN, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(D, SEVEN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("같은 색인지 확인한다")
    void isSameColorTest(final Color color, final boolean expected) {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        final boolean actual = board.isSameColor(new Position(D, EIGHT), color);

        assertThat(actual).isEqualTo(expected);
    }
}
