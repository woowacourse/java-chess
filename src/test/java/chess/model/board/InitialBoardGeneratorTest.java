package chess.model.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitialBoardGeneratorTest {
    private BoardGenerator initialBoardGenerator = new InitialBoardGenerator();

    @Test
    void 보드는_64개_칸으로_구성한다() {
        Board board = initialBoardGenerator.create();
        assertThat(board.getSignatures().size()).isEqualTo(64);
    }

    @Test
    void 보드_생성_시_각_기물을_시작_위치에_초기화한다() {
        Board board = initialBoardGenerator.create();
        String boardSignatures = String.join("", board.getSignatures());
        assertThat(boardSignatures).isEqualTo("RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr");
    }
}
