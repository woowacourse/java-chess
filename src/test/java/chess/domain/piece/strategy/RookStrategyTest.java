package chess.domain.piece.strategy;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.rook.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookStrategyTest {
    private Piece whiteRook;
    private Map<Position, Team> boardDto;

    @BeforeEach
    void setUp() {
        whiteRook = Rook.of(Team.WHITE, Position.of("c4"));
        boardDto = new HashMap<>();
        boardDto.put(Position.of("c4"), Team.WHITE);
    }

    @ParameterizedTest
    @DisplayName("진행 방향에 장애물이 없는 경우")
    @ValueSource(strings = {"c2", "c8", "a4", "h4"})
    void anyOnPath(String target) {
        assertThat(whiteRook.move(Position.of("c4"), Position.of(target), boardDto))
                .isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @DisplayName("도착지에 적팀 기물이 있는 경우")
    @ValueSource(strings = {"c2", "c8", "a4", "h4"})
    void enemyOnTarget(String target) {
        boardDto.put(Position.of(target), Team.BLACK);
        assertThat(whiteRook.move(Position.of("c4"), Position.of(target), boardDto))
                .isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @DisplayName("도착지에 아군 기물이 있는 경우")
    @CsvSource(value = {"c2:c3", "c8:c7", "a4:b4", "h4:f4"}, delimiter = ':')
    void allyOnTarget(String target) {
        boardDto.put(Position.of(target), Team.WHITE);
        assertThatThrownBy(() -> whiteRook.move(Position.of("c4"), Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("목적지에 아군이 존재합니다.");
    }

    @ParameterizedTest
    @DisplayName("진행 방향에 적팀 기물이 있는 경우")
    @CsvSource(value = {"c2:c3", "c8:c7", "a4:b4", "h4:f4"}, delimiter = ':')
    void enemyOnPath(String target, String path) {
        boardDto.put(Position.of(path), Team.BLACK);
        assertThatThrownBy(() -> whiteRook.move(Position.of("c4"), Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 방향에 장애물이 존재합니다.");
    }

    @ParameterizedTest
    @DisplayName("진행 방향에 아군 기물이 있는 경우")
    @CsvSource(value = {"c2:c3", "c8:c7", "a4:b4", "h4:f4"}, delimiter = ':')
    void allyOnPath(String target, String path) {
        boardDto.put(Position.of(path), Team.WHITE);
        assertThatThrownBy(() -> whiteRook.move(Position.of("c4"), Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 방향에 장애물이 존재합니다.");
    }


    @ParameterizedTest
    @DisplayName("진행 방향이 옳지 못한 경우")
    @ValueSource(strings = {"b3", "d8", "a3", "h5"})
    void illegalDirectionTarget(String target) {
        assertThatThrownBy(() -> whiteRook.move(Position.of("c4"), Position.of(target), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 방향으로 이동할 수 없습니다.");
    }
}