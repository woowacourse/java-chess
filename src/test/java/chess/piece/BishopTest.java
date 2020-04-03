package chess.piece;

import static chess.piece.Team.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import chess.validator.BishopMoveValidator;
import chess.validator.MoveValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.position.Position;

public class BishopTest {
	@DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
	@ParameterizedTest
	@CsvSource(value = {"BLACK,B", "WHITE,b"})
	void getSymbolTest(Team team, String expected) {
		Piece piece = new Bishop(team);
		assertThat(piece.getSymbol()).isEqualTo(expected);
	}
}
