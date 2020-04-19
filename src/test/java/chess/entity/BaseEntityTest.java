package chess.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class BaseEntityTest {

	@ParameterizedTest
	@NullSource
	void BaseEntity_NullCreatedTime_ExceptionThrown(final LocalDateTime localDateTime) {
		assertThatThrownBy(() -> ChessGameEntity.of(localDateTime))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("생성 시간이 null입니다.");
	}

	@Test
	void BaseEntity_CreatedTime_GenerateInstance() {
		final BaseEntity baseEntity = ChessGameEntity.of(LocalDateTime.now());

		assertThat(baseEntity).isInstanceOf(BaseEntity.class);
	}

	@Test
	void compareTo_CompareByCreatedTime_ReturnCompareToCreatedTime() {
		final BaseEntity earlyChessGame = ChessGameEntity.of(LocalDateTime.MIN);
		final BaseEntity lateChessGame = ChessGameEntity.of(LocalDateTime.MAX);

		assertThat(earlyChessGame.compareTo(lateChessGame)).isEqualTo(LocalDateTime.MIN.compareTo(LocalDateTime.MAX));
	}

}