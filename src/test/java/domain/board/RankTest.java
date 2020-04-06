package domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.piece.Piece;
import domain.piece.team.Team;

public class RankTest {
	@DisplayName("Rank 생성")
	@Test
	void constructor_GivenPieces_CreateRank() {
		assertThat(new Rank(new ArrayList<>())).isInstanceOf(Rank.class);
	}
}
