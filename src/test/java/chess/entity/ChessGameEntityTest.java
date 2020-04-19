package chess.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class ChessGameEntityTest {

	private static final long DEFAULT_GAME_ID = 0L;

	@Test
	void of_GameIdAndCreatedTime_GenerateInstance() {
		assertThat(ChessGameEntity.of(1, LocalDateTime.now())).isInstanceOf(ChessGameEntity.class);
	}

	@ParameterizedTest
	@NullSource
	void of_NullChessGameEntity_ExceptionThrown(final ChessGameEntity entity) {
		assertThatThrownBy(() -> ChessGameEntity.of(1, entity))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("엔티티가 null입니다.");
	}

	@Test
	void of_GameIdAndChessGameEntity_GenerateInstance() {
		final ChessGameEntity entity = ChessGameEntity.of(1, LocalDateTime.now());

		assertThat(ChessGameEntity.of(1, entity)).isInstanceOf(ChessGameEntity.class);
	}

	@Test
	void of_CreatedTime_GenerateInstance() {
		assertThat(ChessGameEntity.of(LocalDateTime.now())).isInstanceOf(ChessGameEntity.class)
			.extracting("gameId").isEqualTo(DEFAULT_GAME_ID);
	}

}