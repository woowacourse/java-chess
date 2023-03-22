package chess.domain.dto;

import chess.domain.Board;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusDtoTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.create();
    }

    @Test
    @DisplayName("보드가 생성되었을 때 초기 보드의 상태를 가진다.")
    void return_initial_board_status_when_board_is_created() {
        GameStatusDto gameStatusDto = GameStatusDto.from(board);
        Assertions.assertThat(gameStatusDto.getGameStatus())
                .containsExactly(
                        "RNBQKBNR",
                        "PPPPPPPP",
                        "........",
                        "........",
                        "........",
                        "........",
                        "pppppppp",
                        "rnbqkbnr"
                );
    }

    @Test
    @DisplayName("보드 상태를 가진다.")
    void return_board_status() {
        board.move(Square.of(File.D, Rank.TWO), Square.of(File.D, Rank.FOUR));
        board.move(Square.of(File.A, Rank.SEVEN), Square.of(File.A, Rank.FIVE));
        board.move(Square.of(File.C, Rank.ONE), Square.of(File.F, Rank.FOUR));
        GameStatusDto gameStatusDto = GameStatusDto.from(board);
        Assertions.assertThat(gameStatusDto.getGameStatus())
                .containsExactly(
                        "RNBQKBNR",
                        ".PPPPPPP",
                        "........",
                        "P.......",
                        "...p.b..",
                        "........",
                        "ppp.pppp",
                        "rn.qkbnr"
                );
    }
}
