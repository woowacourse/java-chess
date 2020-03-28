package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ChessRankTest {

	@ParameterizedTest
	@ValueSource(ints = {1, 8})
	void from_IntegerChessRank_ReturnInstance(int chessRank) {
		assertThat(ChessRank.from(chessRank)).isInstanceOf(ChessRank.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 9})
	void from_InvalidIntegerChessRank_ExceptionThrown(int chessRank) {
		assertThatThrownBy(() -> ChessRank.from(chessRank))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 랭크가 존재하지 않습니다.");
	}

	@ParameterizedTest
	@ValueSource(chars = {'1', '8'})
	void from_CharacterChessRank_ReturnInstance(char chessRank) {
		assertThat(ChessRank.from(chessRank)).isInstanceOf(ChessRank.class);
	}

	@ParameterizedTest
	@ValueSource(chars = {'0', '9'})
	void from_InvalidCharacterChessRank_ExceptionThrown(char chessRank) {
		assertThatThrownBy(() -> ChessRank.from(chessRank))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 랭크가 존재하지 않습니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"1,1,2", "3,4,7"})
	void move_MovingRankValue_ReturnMovedChessRank(int chessRank, int movingRankValue, int expected) {
		assertThat(ChessRank.from(chessRank).move(movingRankValue)).isEqualTo(ChessRank.from(expected));
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2,1", "8,3,-5"})
	void gapTo_TargetChessRank_CalculateGapFromTargetChessRank(int sourceChessRank, int targetChessRank, int expected) {
		assertThat(ChessRank.from(sourceChessRank).gapTo(ChessRank.from(targetChessRank))).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void gapTo_NullChessRank_ExceptionThrown(ChessRank targetChessRank) {
		ChessRank sourceChessRank = ChessRank.FOUR;

		assertThatThrownBy(() -> sourceChessRank.gapTo(targetChessRank))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 랭크가 null입니다.");
	}

}
