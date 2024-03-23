package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.File;
import chess.domain.piece.Position;
import chess.domain.piece.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MultiDirectionTest {

    @DisplayName("기준위치와 목표위치의 관계가 수직임을 알려준다.")
    @Test
    void ofVERTICAL() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.C, Rank.FIVE);

        // when
        MultiDirection multiDirection = MultiDirection.of(sourcePosition, targetPosition);

        // then
        assertThat(multiDirection).isEqualTo(MultiDirection.VERTICAL);
    }
    @DisplayName("기준위치와 목표위치의 관계가 수평임을 알려준다.")
    @Test
    void ofHORIZONTAL() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.F, Rank.THREE);

        // when
        MultiDirection multiDirection = MultiDirection.of(sourcePosition, targetPosition);

        // then
        assertThat(multiDirection).isEqualTo(MultiDirection.HORIZONTAL);
    }
    @DisplayName("기준위치와 목표위치의 관계가 우상향 대각선임을 알려준다.")
    @Test
    void ofRIGHT_DIAGONAL() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.F, Rank.SIX);

        // when
        MultiDirection multiDirection = MultiDirection.of(sourcePosition, targetPosition);

        // then
        assertThat(multiDirection).isEqualTo(MultiDirection.RIGHT_DIAGONAL);
    }
    @DisplayName("기준위치와 목표위치의 관계가 좌상향 대각임을 알려준다.")
    @Test
    void ofLEFT_DIAGONAL() {
        // given
        Position sourcePosition = new Position(File.C, Rank.THREE);
        Position targetPosition = new Position(File.A, Rank.FIVE);

        // when
        MultiDirection multiDirection = MultiDirection.of(sourcePosition, targetPosition);

        // then
        assertThat(multiDirection).isEqualTo(MultiDirection.LEFT_DIAGONAL);
    }

}
