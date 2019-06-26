package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BoardTest {
    private List<String> basicArrange;
    private List<String> arrange;

    @BeforeEach
    void setUp() {
        basicArrange = Arrays.asList(
                "RNBKQBNR",
                "PPPPPPPP",
                "........",
                "........",
                "........",
                "........",
                "pppppppp",
                "rnbkqbnr");

        arrange = Arrays.asList(
                "....R...",
                "........",
                "........",
                "r.p.R...",
                "........",
                "........",
                "........",
                "....k...");
    }

    @Test
    void create() {
        Board board = BoardFactory.create(basicArrange);

        assertThat(board.get(Point.of(0, 0))
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(Rook.class);
    }

    @Test
    void create2() {
        Board board = BoardFactory.create(arrange);

        assertThat(board.get(Point.of(2, 3))
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(Pawn.class);
    }

    @Test
    void create3() {
        Board board = BoardFactory.create(basicArrange);

        assertThat(board.get(Point.of(7, 0))
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(Rook.class);
    }

    @Test
    void move() {
        Board board = BoardFactory.create(basicArrange);
        board.move(Point.of(1, 1), Point.of(1, 2));
        assertThat(board.get(Point.of(1, 2))
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(Pawn.class);
    }

    @Test
    void move2() {
        Board board = BoardFactory.create(basicArrange);
        board.move(Point.of(1, 1), Point.of(1, 2));
        assertThat(board.get(Point.of(1, 1))).isEqualTo(Optional.empty());
    }

    @Test
    void move_JustMove_True() {
        Board board = BoardFactory.create(arrange);
        assertThat(board.move(Point.of(4, 3), Point.of(4, 7))).isTrue();
    }

    @Test
    void move_SameTeamAttack_False() {
        Board board = BoardFactory.create(arrange);
        assertThat(board.move(Point.of(4, 3), Point.of(4, 0))).isFalse();
    }

    @Test
    void move_BlockOther_False() {
        Board board = BoardFactory.create(arrange);
        assertThat(board.move(Point.of(4, 3), Point.of(0, 3))).isFalse();
    }

    @Test
    void move_Attack_True() {
        Board board = BoardFactory.create(arrange);
        assertThat(board.move(Point.of(4, 3), Point.of(2, 3))).isTrue();
    }

    @Test
    void isKingDead_attackKing_True() {
        Board board = BoardFactory.create(arrange);
        board.move(Point.of(4, 3), Point.of(4, 7));
        assertThat(board.isKingDead()).isTrue();
    }

    @Test
    void isKingDead_attackKing_False() {
        Board board = BoardFactory.create(arrange);
        board.move(Point.of(4, 3), Point.of(4, 6));
        assertThat(board.isKingDead()).isFalse();
    }

    @Test
    void test() {
        List<String> list = Arrays.asList(
                "..Kk....",
                "........",
                "........",
                "........",
                "...P....",
                "....p...",
                "........",
                "........");
        Board board = BoardFactory.create(list);
        board.move(Point.of(4, 5), Point.of(3, 4));
        board.mappingBoardToString().forEach(System.out::println);
    }

    @Test
    void calculateScore() {
        Board board = BoardFactory.create(basicArrange);
        assertThat(board.calculateScore(PlayerType.WHITE)).isEqualTo(38);
    }

    @Test
    void convert() {
        Board board = BoardFactory.create(basicArrange);
        board.mappingBoardToString().forEach(System.out::println);
    }
}
