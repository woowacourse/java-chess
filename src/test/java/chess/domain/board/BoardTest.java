package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.GamePiece;

class BoardTest {

    @Test
    @DisplayName("보드 생성")
    void create() {
        Board board = Board.createInitial();
        String map = board.getBoard()
                .values()
                .stream()
                .map(GamePiece::getName)
                .collect(Collectors.joining(""));
        assertThat(map).isEqualTo("RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr");
    }
}