package chess.domain.direction;

import chess.domain.piece.core.Square;
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
                new Square(3,2),
                new Square(3,1),
                new Square(3,0)
        );
        this.route = new Route(squares);
    }

    @Test
    void Route객체_생성_테스트() {
        assertThat(this.route).isEqualTo(new Route(Arrays.asList(
                new Square(3,2),
                new Square(3,1),
                new Square(3,0)
        )));
    }

    @Test
    void Route_크기_테스트() {
        assertThat(this.route.size()).isEqualTo(3);
    }

    @Test
    void squares_반환하기_테스트() {
        assertThat(this.route.get(1)).isEqualTo(new Square(3,1));
    }

    @AfterEach
    void tearDown() {
        this.squares = null;
        this.route = null;
    }
}
