package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Blank;
import chess.domain.piece.Position;

public class RankTest {
	@Test
	@DisplayName("체스판 한 줄이 정상적으로 생성된 경우")
	void constructor() {
		assertThat(new Rank(Arrays.asList(
				new Blank(Position.from("a1")),
				new Blank(Position.from("b1")),
				new Blank(Position.from("c1")),
				new Blank(Position.from("d1")),
				new Blank(Position.from("e1")),
				new Blank(Position.from("f1")),
				new Blank(Position.from("g1")),
				new Blank(Position.from("h1"))
		))).isInstanceOf(Rank.class);
	}

	@Test
	@DisplayName("체스판 한 줄이 8개로 이루어지지 않은 경우 예외 발생")
	void constructor_invalid_size() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Rank(Arrays.asList(
				new Blank(Position.from("a1")),
				new Blank(Position.from("b1")),
				new Blank(Position.from("c1")),
				new Blank(Position.from("d1"))
		)));
	}

	@Test
	@DisplayName("Blank 한 줄 생성")
	void createBlanks() {
		assertThat(Rank.createBlanks(0)).isInstanceOf(Rank.class);
	}
}
