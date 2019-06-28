package chess.domain.direction;

import chess.domain.board.Board;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteTest {
    private List<Square> squares;
    private Route route;

    @BeforeEach
    void setUp() {
        this.squares = Arrays.asList(
                Square.of(3, 0),
                Square.of(3, 1),
                Square.of(3, 2)
        );
        this.route = new Route(squares, MoveStrategy.BOTH);
    }

    @Test
    void Route객체_생성_테스트() {
        assertThat(this.route).isEqualTo(new Route(Arrays.asList(
                Square.of(3, 0),
                Square.of(3, 1),
                Square.of(3, 2)
        ), MoveStrategy.BOTH));
    }

    @Test
    void Route_크기_테스트() {
        assertThat(this.route.size()).isEqualTo(3);
    }

    @Test
    void 출발_Square_반환하기_테스트() {
        assertThat(route.getSourceSquare()).isEqualTo(Square.of(3, 0));
    }

    @Test
    void 도착_Square_반환하기_테스트() {
        assertThat(route.getTargetSquare()).isEqualTo(Square.of(3, 2));
    }

    @Test
    void 경로중_블록이_있는지_확인_테스트() {
        assertThat(route.canMove(Board.drawBoard())).isFalse();
    }

    @Test
    void 경로_제대로_생성하는지_확인() {
        Board board = Board.drawBoard();
        Piece piece = board.getPiece(Square.of(0, 6));
        Route route = piece.getRoute(Square.of(0, 6), Square.of(0, 4));
        assertThat(route).isEqualTo(new Route(Arrays.asList(
                Square.of(0, 6),
                Square.of(0, 5),
                Square.of(0, 4)
        ), MoveStrategy.ONLY_EMPTY));
        assertThat(route.canMove(board)).isTrue();
    }

    @AfterEach
    void tearDown() {
        this.squares = null;
        this.route = null;
    }
}
