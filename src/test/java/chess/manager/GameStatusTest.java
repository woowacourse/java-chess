package chess.manager;

import chess.domain.board.BoardInitializer;
import chess.domain.piece.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatusTest {

    private GameStatus gameStatus;

    @BeforeEach
    void setUp() {
        gameStatus = GameStatus.statusOfBoard(BoardInitializer.initiateBoard());
    }

    @Test
    @DisplayName("객체 잘 생성된다.")
    void createStatusTest() {
        assertThat(gameStatus).isInstanceOf(GameStatus.class);
        assertThat(gameStatus.blackScore()).isEqualTo(38.0d);
        assertThat(gameStatus.whiteScore()).isEqualTo(38.0d);
    }

    @Test
    @DisplayName("승자 판단해준다.")
    void judgeWinnerTest() {
        Owner owner = gameStatus.judgeWinner();

        assertThat(owner).isEqualTo(Owner.NONE);
    }
}