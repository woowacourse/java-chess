package chess.model.board;

import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.BISHOP;
import static chess.model.piece.PieceType.KING;
import static chess.model.piece.PieceType.KNIGHT;
import static chess.model.piece.PieceType.PAWN;
import static chess.model.piece.PieceType.QUEEN;
import static chess.model.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Type;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }

    @Test
    @DisplayName("64개의 칸을 가지는 체스판을 생성한다.")
    void constructor_whenCall_thenSuccess() {
        assertAll(
                () -> assertThat(board).isExactlyInstanceOf(Board.class),
                () -> assertThat(board)
                        .extracting("squares", InstanceOfAssertFactories.list(Square.class))
                        .hasSize(64)
        );
    }

    /**
     * rank = 8 RNBQKBNR
     * rank = 7 PPPPPPPP
     * rank = 2 pppppppp
     * rank = 1 rnbqkbnr
     */
    @Test
    @DisplayName("체스판에 정상적으로 기물이 생성 되었는지 확인한다.")
    void constructor_whenCall_thenReturnPieces() {
        //given
        final List<Square> squares = board.getSquares();

        //when
        final List<Type> rankOne = getRank(squares, 0, 8);
        final List<Type> rankTwo = getRank(squares, 8, 16);
        final List<Type> rankSeven = getRank(squares, 48, 56);
        final List<Type> rankEight = getRank(squares, 56, 64);

        //then
        assertAll(
                () -> assertThat(rankOne).containsExactly(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP,
                        KNIGHT, ROOK),
                () -> assertThat(rankTwo).containsExactly(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                        PAWN),
                () -> assertThat(rankSeven).containsExactly(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN,
                        PAWN,
                        PAWN),
                () -> assertThat(rankEight).containsExactly(ROOK, KNIGHT, BISHOP, QUEEN, KING,
                        BISHOP,
                        KNIGHT, ROOK)
        );
    }

    private List<Type> getRank(final List<Square> squares, final int from, final int to) {
        return squares.subList(from, to).stream()
                .map(Square::getType)
                .collect(Collectors.toList());
    }

    @Test
    @DisplayName("빈 Square를 선택하면 예외가 발생한다.")
    void move_givenEmptySource_thenFail() {
        // given
        final Position emptySource = new Position(File.F, Rank.FIFTH);
        final Position target = new Position(File.H, Rank.THIRD);

        // when, then
        assertThatThrownBy(() -> board.move(emptySource, target, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @Test
    @DisplayName("상대방의 기물이 있는 Square를 선택하면 예외가 발생한다.")
    void move_givenEnemySource_thenFail() {
        // given
        final Position enemySource = new Position(File.F, Rank.EIGHTH);
        final Position target = new Position(File.H, Rank.THIRD);

        // when, then
        assertThatThrownBy(() -> board.move(enemySource, target, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자신의 기물이 아닙니다.");
    }

    @Test
    @DisplayName("이동 중에 기물이 존재하는 칸을 만나면 예외가 발생한다.")
    void move_givenInvalidSourceAndTarget_thenFail() {
        // given
        final Position source = new Position(File.F, Rank.FIRST);
        final Position target = new Position(File.H, Rank.THIRD);

        // when, then
        assertThatThrownBy(() -> board.move(source, target, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다");
    }

}
