package domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.piece.Piece;
import domain.piece.position.Column;
import domain.piece.position.Position;
import domain.piece.position.Row;

public class RankTest {
	List<Rank> ranks = BoardFactory.create().getRanks();

	@DisplayName("Rank 생성")
	@Test
	void constructor_GivenPieces_CreateRank() {
		assertThat(new Rank(new ArrayList<>())).isInstanceOf(Rank.class);
	}

	@DisplayName("해당 포지션에 Piece가 존재하는지 검사")
	@ParameterizedTest
	@CsvSource({"A,ONE,true", "H,EIGHT,true", "D,FIVE,false", "A,THREE,false"})
	void findPiece_GivenPosition_ReturnOptional(Column column, Row row, boolean expected) {
		Position position = Position.of(column, row);
		Rank rank = ranks.get(row.getRankIndex());
		Optional<Piece> piece = rank.findPiece(position);
		assertThat(piece.isPresent()).isEqualTo(expected);
	}

	@DisplayName("각 라인마다 왕이 몇 개 존재하는지 검사")
	@ParameterizedTest
	@CsvSource({"0,1","1,0","2,0","3,0","4,0","5,0","6,0","7,1"})
	void countOfKing_GivenRank_ReturnCountOfKing(int rankIndex, int expected) {
		assertThat(ranks.get(rankIndex).countOfKing()).isEqualTo(expected);
	}
}
