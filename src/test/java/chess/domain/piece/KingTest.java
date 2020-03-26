package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.player.Player;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    private PieceState whiteKing;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteKing = King.of(Position.of("C4"), Player.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(King.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly(String target) {
        boardDto.put(Position.of(target), new PieceDto(Player.WHITE));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B3", "C5", "D5", "D4", "D3", "C3", "B5"})
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy(String target) {
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);
        assertThat(whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(King.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteKing.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "A4", "A6", "C6", "E6", "E4", "E2", "C2"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        boardDto.put(Position.of(target), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteKing.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }
}