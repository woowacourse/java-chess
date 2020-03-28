package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {

    private PieceState whiteKnight;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteKnight = Knight.of(Position.of("b1"), Team.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @Test
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        boardDto.put(Position.of("C3"), new PieceDto(Team.WHITE));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteKnight.move(Position.of("C3"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        boardDto.put(Position.of("C3"), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);
        assertThat(whiteKnight.move(Position.of("C3"), boardState))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteKnight.move(Position.of("C3"), boardState))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException() {
        assertThatThrownBy(() -> whiteKnight.move(Position.of("C4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        boardDto.put(Position.of("C4"), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteKnight.move(Position.of("C4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
