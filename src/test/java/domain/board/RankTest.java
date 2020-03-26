package domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {
	@DisplayName("Rank 생성")
	@Test
	void constructor_GivenPieces_CreateRank() {
		assertThat(new Rank(new ArrayList<>())).isInstanceOf(Rank.class);
	}
}
