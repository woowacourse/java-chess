package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class BoardTest {
	private Board board;
	private Rook rook;
	private Position from;

	@BeforeEach
	void setup() {
		Map<Position, Piece> pieces = new HashMap<>();
		rook = new Rook(Team.WHITE);
		from = Position.of("a1");
		pieces.put(from, rook);
		board = new Board(pieces);
	}

	@DisplayName("Board객체 생성 테스트")
	@Test
	void constructTest() {
		assertThat(board).isInstanceOf(Board.class);
	}

	@DisplayName("move메서드를 실행하면 Map이 수정되는지 테스트")
	@Test
	void moveTest() {
		Position from = Position.of("a1");
		Position to = Position.of("a2");
		board.move(from, to);
		assertThat(board.findPiece(to)).isEqualTo(rook);
	}

	@DisplayName("기물이 없는 빈칸을 출발점으로 설정한 move메서드를 실행시 예외 발생 확인")
	@Test
	void move_blank_position_to_start_exception_Test() {
		Position from = Position.of("a2");
		Position to = Position.of("a5");
		assertThatThrownBy(() -> board.move(from, to))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("position에 위치해있는 기물이 team과 같은 팀이 아니면 true 반환")
	@ParameterizedTest
	@CsvSource({"WHITE,false", "NONE,true", "BLACK,true"})
	void isNotSameTeamFromPositionTest(Team team, boolean expected) {
		boolean actual = board.isNotSameTeamFromPosition(from, team);
		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("점수 계산 테스트")
	@Test
	void calculateScoreTest() {
		board.start();
		Map<Team, Double> status = board.status();
		assertThat(status).containsOnly(entry(Team.BLACK, 38.0), entry(Team.WHITE, 38.0));
	}
}
