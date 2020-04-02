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

	@DisplayName("처음 보드판에서 Pawn을 찾아 반환")
	@ParameterizedTest
	@CsvSource({"0,0,WHITE","1,8,WHITE","6,8,BLACK","7,0,BLACK"})
	void findPawn_InitialBoard_ReturnPawns(int index, int count, Team team) {
		Board board = new BoardGame().getBoard();
		List<Piece> pawn =  board.getRanks().get(index).findPawn(team);
		assertThat(pawn.size()).isEqualTo(count);
	}
}
