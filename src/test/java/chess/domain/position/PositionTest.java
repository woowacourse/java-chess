package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

	@ParameterizedTest
	@CsvSource(value = {"a,2", "d,8", "h,5"})
	void of_ChessFileAndChessRank_GenerateInstance(char chessFile, int chessRank) {
		assertThat(Position.of(ChessFile.from(chessFile), ChessRank.from(chessRank))).isInstanceOf(Position.class);
	}

	@ParameterizedTest
	@NullSource
	void of_NullChessFile_ExceptionThrown(ChessFile chessFile) {
		ChessRank chessRank = ChessRank.from(4);

		assertThatThrownBy(() -> Position.of(chessFile, chessRank))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 파일이 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void of_NullChessRank_ExceptionThrown(ChessRank chessRank) {
		ChessFile chessFile = ChessFile.from('h');

		assertThatThrownBy(() -> Position.of(chessFile, chessRank))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 랭크가 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"a2", "d8", "h5"})
	void of_KeyOfPosition_GenerateInstance(String key) {
		assertThat(Position.of(key)).isInstanceOf(Position.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validateEmpty_InvalidKey_ExceptionThrown(String key) {
		assertThatThrownBy(() -> Position.of(key))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 위치 입력이 아닙니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"a12", "hi9", "b"})
	void validateLength_InvalidLengthKey_ExceptionThrown(String key) {
		assertThatThrownBy(() -> Position.of(key))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 위치 입력이 아닙니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"0,1,b2,b3", "2,4,b3,d7"})
	void move_MovingFileValueAndRankValue_ReturnMovedPosition(int movingFileValue, int movingRankValue,
		Position position, Position expected) {
		assertThat(position.move(movingFileValue, movingRankValue)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"a2,c4,2", "h6,f8,-2"})
	void calculateChessFileGapTo_TargetPosition_ChessFileGapFromTargetPosition(Position sourcePosition,
		Position targetPosition, int expected) {
		assertThat(sourcePosition.calculateChessFileGapTo(targetPosition)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void calculateChessFileGapTo_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		Position sourcePosition = Position.of("b2");

		assertThatThrownBy(() -> sourcePosition.calculateChessFileGapTo(targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"a2,c4,2", "f4,f8,4"})
	void calculateRankGapTo_TargetPosition_ChessRankGapFromTargetPosition(Position sourcePosition,
		Position targetPosition, int expected) {
		assertThat(sourcePosition.calculateChessRankGapTo(targetPosition)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void calculateChessRankGapTo_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		Position sourcePosition = Position.of("b2");

		assertThatThrownBy(() -> sourcePosition.calculateChessRankGapTo(targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"c,true", "d,false"})
	void isSame_CompareChessFile_ReturnCompareResult(char inputChessFile, boolean expected) {
		Position position = Position.of("c3");
		ChessFile chessFile = ChessFile.from(inputChessFile);

		assertThat(position.isSame(chessFile)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void isSame_NullChessFile_ExceptionThrown(ChessFile chessFile) {
		assertThatThrownBy(() -> Position.of("c3").isSame(chessFile))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 파일이 null입니다.");
	}

}
