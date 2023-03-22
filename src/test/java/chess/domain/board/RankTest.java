package chess.domain.board;

import static chess.domain.board.Rank.INVALID_RANK_SIZE;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.sliding.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;

class RankTest {

    private Rank rank;

    @BeforeEach
    void setUp() {
        var rank = IntStream.range(0, 8)
                .mapToObj(col -> new Square(new Position(0, col), new EmptyPiece()))
                .collect(toList());

        this.rank = new Rank(rank);
    }

    @Test
    @DisplayName("List<Square>의 사이즈가 8이 아니면 예외를 발생한다.")
    void create_validate_size() {
        var rank = IntStream.range(0, 9)
                .mapToObj(col -> new Square(new Position(0, col), new EmptyPiece()))
                .collect(toList());

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Rank(rank)
        ).withMessage(INVALID_RANK_SIZE + rank.size());
    }

    @Test
    @DisplayName("해당 Square에 Piece가 없으면 true를 반환한다.")
    void isEmptyPieceTest_true() {
        assertThat(rank.isEmptyPiece(0)).isTrue();
    }

    @Test
    @DisplayName("해당 Square에 Piece가 있으면 false를 반환한다.")
    void isExistPieceTest_false() {
        var squares = IntStream.range(0, 8)
                .mapToObj(col -> new Square(new Position(0, col), new Rook(Team.BLACK)))
                .collect(toList());
        var rank = new Rank(squares);

        assertThat(rank.isEmptyPiece(0)).isFalse();
    }

    @Test
    @DisplayName("원하는 인덱스의 Sqaure를 조회한다.")
    void findSquareTest() {
        assertThat(rank.findSquareAt(0)).isEqualTo(new Square(new Position(0, 0), new EmptyPiece()));
    }

    @Test
    @DisplayName("원하는 인덱스의 Square를 교체한다.")
    void replaceSquareTest() {
        rank.replaceSquare(0, new Square(new Position(0, 0), new Rook(Team.BLACK)));

        assertThat(rank.getRank().get(0).getPiece()).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("원하는 인덱스의 Piece를 교체한다.")
    void replacePieceTest() {
        rank.replacePiece(0, new Rook(Team.WHITE));

        assertThat(rank.getRank().get(0).getPiece()).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("원하는 인덱스의 Piece를 반환한다.")
    void findPieceTest() {
        assertThat(rank.findPieceAt(0)).isInstanceOf(EmptyPiece.class);
    }
}
