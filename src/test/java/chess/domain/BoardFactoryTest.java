package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardFactoryTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void init() {
        board = BoardFactory.getInitialPieces();
    }

    @Test
    @DisplayName("폰들을 생성해서 매핑해준다.")
    void initPawnPosition() {
        List<Position> expected = new ArrayList<>();
        for (Abscissa value : Abscissa.values()) {
            expected.add(Position.valueOf(value + Ordinate.TWO.getValue()));
            expected.add(Position.valueOf(value + Ordinate.SEVEN.getValue()));
        }

        assertThat(board.keySet()).containsAll(expected);
    }

    @Test
    @DisplayName("룩들을 생성해서 매핑해준다.")
    void initRookPosition() {
        List<Position> expected = new ArrayList<>();
        expected.add(Position.valueOf("a1"));
        expected.add(Position.valueOf("h1"));
        expected.add(Position.valueOf("a8"));
        expected.add(Position.valueOf("h8"));

        assertThat(board.keySet()).containsAll(expected);
    }

    @Test
    @DisplayName("나이트들을 생성해서 매핑해준다.")
    void initKnightPosition() {
        List<Position> expected = new ArrayList<>();
        expected.add(Position.valueOf("b1"));
        expected.add(Position.valueOf("g1"));
        expected.add(Position.valueOf("b8"));
        expected.add(Position.valueOf("g8"));

        assertThat(board.keySet()).containsAll(expected);
    }

    @Test
    @DisplayName("비숍들을 생성해서 매핑해준다.")
    void initBishopPosition() {
        List<Position> expected = new ArrayList<>();
        expected.add(Position.valueOf("c1"));
        expected.add(Position.valueOf("f1"));
        expected.add(Position.valueOf("c8"));
        expected.add(Position.valueOf("f8"));

        assertThat(board.keySet()).containsAll(expected);
    }

    @Test
    @DisplayName("킹들을 생성해서 매핑해준다.")
    void initKingPosition() {
        List<Position> expected = new ArrayList<>();
        expected.add(Position.valueOf("e1"));
        expected.add(Position.valueOf("e8"));

        assertThat(board.keySet()).containsAll(expected);
    }

    @Test
    @DisplayName("퀸들을 생성해서 매핑해준다.")
    void initQueenPosition() {
        List<Position> expected = new ArrayList<>();
        expected.add(Position.valueOf("d1"));
        expected.add(Position.valueOf("d8"));

        assertThat(board.keySet()).containsAll(expected);
    }
}
