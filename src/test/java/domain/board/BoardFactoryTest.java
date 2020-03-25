package domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.position.Column;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class BoardFactoryTest {
	@DisplayName("체스 보드판 생성")
	@Test
	void create_CreateInitialBoard() {
		assertThat(BoardFactory.create()).isInstanceOf(Board.class);
	}

	@DisplayName("각 말의 위치 확인")
	@MethodSource("initRanks")
	@ParameterizedTest
	void create_initializedBoard_True(List<Rank> expectedRanks) {
		List<Rank> ranks = BoardFactory.create().getRanks();
		for (int i = 0; i < expectedRanks.size(); i++) {
			assertThat(ranks.get(i)).isEqualTo(expectedRanks.get(i));
		}
	}

	private static Stream<Arguments> initRanks() {
		List<Rank> ranks = new ArrayList<>();

		List<Piece> first = new ArrayList<>();
		first.add(new Rook(Position.of("a1"), Team.WHITE));
		first.add(new Knight(Position.of("b1"), Team.WHITE));
		first.add(new Bishop(Position.of("c1"), Team.WHITE));
		first.add(new King(Position.of("d1"), Team.WHITE));
		first.add(new Queen(Position.of("e1"), Team.WHITE));
		first.add(new Bishop(Position.of("f1"), Team.WHITE));
		first.add(new Knight(Position.of("g1"), Team.WHITE));
		first.add(new Rook(Position.of("h1"), Team.WHITE));
		ranks.add(new Rank(first));

		List<Piece> second = new ArrayList<>();
		for (Column column : Column.values()) {
			second.add(new Pawn(Position.of(column.getColumnName() + "2"), Team.WHITE));
		}
		ranks.add(new Rank(second));

		for (int i = 0; i < 4; i++) {
			ranks.add(new Rank(new ArrayList<>()));
		}

		List<Piece> seventh = new ArrayList<>();
		seventh.add(new Rook(Position.of("a7"), Team.BLACK));
		seventh.add(new Knight(Position.of("b7"), Team.BLACK));
		seventh.add(new Bishop(Position.of("c7"), Team.BLACK));
		seventh.add(new King(Position.of("d7"), Team.BLACK));
		seventh.add(new Queen(Position.of("e7"), Team.BLACK));
		seventh.add(new Bishop(Position.of("f7"), Team.BLACK));
		seventh.add(new Knight(Position.of("g7"), Team.BLACK));
		seventh.add(new Rook(Position.of("h7"), Team.BLACK));
		ranks.add(new Rank(seventh));

		List<Piece> eighth = new ArrayList<>();
		for (Column column : Column.values()) {
			eighth.add(new Pawn(Position.of(column.getColumnName() + "8"), Team.BLACK));
		}
		ranks.add(new Rank(eighth));

		return Stream.of(Arguments.of(ranks));
	}
}
