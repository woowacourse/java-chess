package chess.domain.board;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static chess.fixture.PositionFixture.E4;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BoardTest {

    private static List<PieceType> generateResult(final Board board) {
        final List<Rank> ranks = Arrays.stream(Rank.values())
                .collect(toList());
        Collections.reverse(ranks);
        final Map<Position, Piece> result = board.getBoard();
        return ranks.stream()
                .flatMap(file -> Arrays.stream(File.values()).map(rank -> Position.of(rank, file)))
                .map(result::get)
                .map(Piece::type)
                .collect(toList());
    }

    @Test
    void 체스판을_초기화한다() {
        // given
        final Board board = Board.initialize();

        // expect
        final List<PieceType> result = generateResult(board);
        assertThat(result).containsExactly(
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK
        );
    }

    @Test
    void 불가능한_이동_커맨드를_입력받는_경우_예외를_던진다() {
        // given
        final Board board = Board.initialize();

        // expect
        assertThatThrownBy(() -> board.move("d2", "d5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 이동 명령어입니다.");
    }

    @Test
    void 이동_가능한_커맨드를_입력받는_경우_기물을_이동한다() {
        // given
        final Board board = Board.initialize();

        // when
        board.move("e2", "e4");

        // then
        assertThat(board.getBoard().get(E4)).isEqualTo(Pawn.from(Color.WHITE));
    }

    @Test
    void 상대편의_기물을_이동하려는_경우_예외를_던진다() {
        // given
        final Board board = Board.initialize();

        // expect
        assertThatThrownBy(() -> board.move("g7", "g6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 기물을 움직일 수 없습니다.");
    }

    @Test
    void 이동_경로에_기물이_있는_경우_예외를_던진다() {
        // given
        final Board board = Board.initialize();

        // expect
        assertThatThrownBy(() -> board.move("d1", "d4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 기물이 존재합니다.");
    }

    @Test
    void 루이로페즈_모던_슈타이니츠_바리에이션_으로_게임을_진행한다() {
        // given
        final Board board = Board.initialize();

        // when
        board.move("e2", "e4");
        board.move("e7", "e5");
        board.move("g1", "f3");
        board.move("b8", "c6");
        board.move("f1", "b5");
        board.move("a7", "a6");
        board.move("b5", "a4");
        board.move("d7", "d6");

        // then
        final List<PieceType> result = generateResult(board);
        assertThat(result).containsExactly(
                ROOK, EMPTY, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK,
                EMPTY, PAWN, PAWN, EMPTY, EMPTY, PAWN, PAWN, PAWN,
                PAWN, EMPTY, KNIGHT, PAWN, EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                BISHOP, EMPTY, EMPTY, EMPTY, PAWN, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, KNIGHT, EMPTY, EMPTY,
                PAWN, PAWN, PAWN, PAWN, EMPTY, PAWN, PAWN, PAWN,
                ROOK, KNIGHT, BISHOP, QUEEN, KING, EMPTY, EMPTY, ROOK
        );
    }
}
