package chess.domain.state;

import chess.domain.Board;
import chess.domain.BoardFixture;
import chess.domain.Score;
import chess.domain.postion.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.postion.File.A;
import static chess.domain.postion.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WhiteTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = BoardFixture.setup();
    }

    @DisplayName("White 상태에서 턴이 바뀌면 Black 상태로 되는지 테스트")
    @Test
    void changeTurn() {
        White white = new White(board);
        List<Position> positions = List.of(new Position(A, TWO), new Position(A, THREE));

        assertThat(white.changeTurn(positions)).isInstanceOf(Black.class);
    }

    @DisplayName("White 상태에서 엔드하면 게임이 종료되되는지 테스트")
    @Test
    void end() {
        White white = new White(board);

        assertThatThrownBy(white::end).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("흰색 턴에 검은색 기물을 움직이도록 하면 에러 테스트")
    @Test
    void isNotWhitePiece() {
        White white = new White(board);
        List<Position> positions = List.of(new Position(A, SEVEN), new Position(A, SIX));

        assertThatThrownBy( () -> white.changeTurn(positions));
    }

    @DisplayName("status시 Board로 부터 Map으로된 점수를 받아오는지 테스트")
    @Test
    void status() {
        White white = new White(board);

        assertThat(white.status()).isInstanceOf(Score.class);
    }
}
