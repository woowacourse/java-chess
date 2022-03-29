package chess.domain.state;

import chess.domain.Board;


import chess.domain.BoardFixture;
import chess.domain.Score;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.PositionFixture.BLACK_SOURCE;
import static chess.domain.PositionFixture.BLACK_TARGET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BlackTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = BoardFixture.setup();
    }

    @DisplayName("Black 상태에서 턴이 바뀌면 White 상태로 되는 지 테스트")
    @Test
    void changeTurn() {
        Black black = new Black(board);

        assertThat(black.changeTurn(BLACK_SOURCE, BLACK_TARGET)).isInstanceOf(White.class);
    }

    @DisplayName("검은색 턴에 흰색 기물을 움직이도록 하면 에러 테스트")
    @Test
    void isNotBlackPiece() {
        Black black = new Black(board);

        assertThatThrownBy( () -> black.changeTurn(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE)));
    }

    @DisplayName("status시 Board로 부터 Map으로된 점수를 받아오는지 테스트")
    @Test
    void status() {
        Black black = new Black(board);

        assertThat(black.status()).isInstanceOf(Score.class);
    }
}
