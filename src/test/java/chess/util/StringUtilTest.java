package chess.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class StringUtilTest {

	@ParameterizedTest
	@NullSource
	void splitChessCommand_NullChessCommand_ExceptionThrown(final String chessCommand) {
		assertThatThrownBy(() -> StringUtil.splitChessCommand(chessCommand))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("분리할 명령어가 null입니다.");
	}

	@Test
	void splitChessCommand_ChessCommand_ReturnListOfSpiltChessCommand() {
		final String chessCommand = "move b1 b2";

		final List<String> expected = Arrays.asList("move", "b1", "b2");
		assertThat(StringUtil.splitChessCommand(chessCommand)).isEqualTo(expected);
	}

}