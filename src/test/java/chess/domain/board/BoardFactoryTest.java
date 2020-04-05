package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.GamePiece;
import chess.domain.player.User;

public class BoardFactoryTest {

    @Test
    @DisplayName("보드 생성")
    void create() {
        Board board = BoardFactory.createInitialBoard(User.EMPTY_BOARD_USER, User.EMPTY_BOARD_USER);
        String map = board.getBoard()
                .values()
                .stream()
                .map(GamePiece::getName)
                .collect(Collectors.joining(""));
        assertThat(map).isEqualTo("RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr");
    }
}
