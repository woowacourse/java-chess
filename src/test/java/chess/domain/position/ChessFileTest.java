package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ChessFileTest {

	@ParameterizedTest
	@ValueSource(chars = {'a', 'h'})
	void from_CharacterChessFile_ReturnInstance(final char chessFile) {
		assertThat(ChessFile.from(chessFile)).isInstanceOf(ChessFile.class);
	}

	@ParameterizedTest
	@ValueSource(chars = {'i', 'z'})
	void from_InvalidCharacterChessFile_ExceptionThrown(final char chessFile) {
		assertThatThrownBy(() -> ChessFile.from(chessFile))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 파일이 존재하지 않습니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"a", "h"})
	void from_StringChessFile_ReturnInstance(final String chessFile) {
		assertThat(ChessFile.from(chessFile)).isInstanceOf(ChessFile.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"i", "z"})
	void from_InvalidStringChessFile_ExceptionThrown(final String chessFile) {
		assertThatThrownBy(() -> ChessFile.from(chessFile))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 파일이 존재하지 않습니다.");
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 8})
	void from_IntegerFileValue_ReturnInstance(final int fileValue) {
		assertThat(ChessFile.from(fileValue)).isInstanceOf(ChessFile.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 9})
	void from_InvalidIntegerFileValue_ExceptionThrown(final int fileValue) {
		assertThatThrownBy(() -> ChessFile.from(fileValue))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 파일이 존재하지 않습니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"a,1,b", "c,4,g"})
	void move_MovingFileValue_ReturnMovedChessFile(final String chessFile, final int movingFileValue,
		final String expected) {
		assertThat(ChessFile.from(chessFile).move(movingFileValue)).isEqualTo(ChessFile.from(expected));
	}

	@ParameterizedTest
	@CsvSource(value = {"a,b,1", "h,c,-5"})
	void gapTo_TargetChessFile_CalculateGapFromTargetChessFile(final String sourceChessFile,
		final String targetChessFile,
		final int expected) {
		assertThat(ChessFile.from(sourceChessFile).gapTo(ChessFile.from(targetChessFile))).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void gapTo_NullChessFile_ExceptionThrown(final ChessFile targetChessFile) {
		final ChessFile sourceChessFile = ChessFile.E;

		assertThatThrownBy(() -> sourceChessFile.gapTo(targetChessFile))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 파일이 null입니다.");
	}

}
