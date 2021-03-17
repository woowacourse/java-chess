package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private final Board board = Board.getInstance();

    @DisplayName("8 * 8 의 빈 체스 판 싱글톤 테스트")
    @Test
    void singleton() {
        Board board1 = Board.getInstance();
        Board board2 = Board.getInstance();

        assertThat(board1).isSameAs(board2);
    }

    @DisplayName("초기 기물 배치 - 7Rank는 모두 흑의 폰이다.")
    @Test
    void boardInitialization_BlackPawn() {
        board.initialize();

        boolean isAllBlackPawn = Arrays.stream(File.values())
            .map(file -> board.of(new Coordinate(file, Rank.SEVEN)).getName())
            .allMatch(name -> name.equals("P"));

        assertThat(isAllBlackPawn).isTrue();
    }

    @DisplayName("초기 기물 배치 - 2Rank는 모두 백의 폰이다.")
    @Test
    void boardInitialization_WhitePawn() {
        board.initialize();

        boolean isAllWhitePawn = Arrays.stream(File.values())
            .map(file -> board.of(new Coordinate(file, Rank.TWO)).getName())
            .allMatch(name -> name.equals("p"));

        assertThat(isAllWhitePawn).isTrue();
    }
}