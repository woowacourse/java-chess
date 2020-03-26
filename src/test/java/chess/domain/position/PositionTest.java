package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

public class PositionTest {
	@Test
	void Position_FileAndRank_GenerateInstance() {
		assertThat(Position.of(ChessFile.from('a'), ChessRank.from(2))).isInstanceOf(Position.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validateEmpty_InvalidKey_ExceptionThrown(String emptyValue) {
		assertThatThrownBy(() -> Position.of(emptyValue))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 위치 입력이 아닙니다.");
	}

	@Test
	void validateLength_InvalidLengthKey_ExceptionThrown() {
		assertThatThrownBy(() -> Position.of("d12"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 위치 입력이 아닙니다.");
	}

	@Test
	void move_AdditionalFileAndRank_ReturnMovedPosition() {
		Position sourcePosition = Position.of("b2");

		Position expected = Position.of("b3");
		assertThat(sourcePosition.move(0, 1)).isEqualTo(expected);
	}

	@Test
	void calculateFileIntervalTo_TargetPosition_FileIntervalByTargetPosition() {
		Position sourcePosition = Position.of(ChessFile.from('a'), ChessRank.from(2));
		Position targetPosition = Position.of(ChessFile.from('c'), ChessRank.from(4));

		assertThat(sourcePosition.calculateChessFileGapTo(targetPosition)).isEqualTo(2);
	}

	@Test
	void calculateRankIntervalTo_TargetPosition_RankIntervalByTargetPosition() {
		Position sourcePosition = Position.of(ChessFile.from('a'), ChessRank.from(2));
		Position targetPosition = Position.of(ChessFile.from('c'), ChessRank.from(4));

		assertThat(sourcePosition.calculateChessRankGapTo(targetPosition)).isEqualTo(2);
	}

	@ParameterizedTest
	@NullSource
	void calculateFileIntervalTo_NullPosition_ExceptionThrown(Position nullPosition) {
		assertThatThrownBy(() -> Position.of("b2").calculateChessFileGapTo(nullPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("비교할 타겟 위치가 존재하지 않습니다.");
	}

	@ParameterizedTest
	@NullSource
	void calculateRankIntervalTo_NullPosition_ExceptionThrown(Position nullPosition) {
		assertThatThrownBy(() -> Position.of("b2").calculateChessRankGapTo(nullPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("비교할 타겟 위치가 존재하지 않습니다.");
	}

	@Test
	void toString_Position_JoinFileAndRank() {
		Position position = Position.of(ChessFile.from('a'), ChessRank.from(2));

		String expected = "a2";
		assertThat(position.toString()).isEqualTo(expected);
	}
}
