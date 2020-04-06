package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Board;
import chess.domain.Status;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.position.Position;

class PlayingTest {
	private Playing playing;

	@BeforeEach
	void setUp() {
		playing = new Playing(BoardRepository.create(), new Turn(Team.WHITE));
	}

	@Test
	void isEnd() {
		assertThat(playing.isEnd()).isFalse();
	}

	@Test
	void start() {
		assertThatThrownBy(() -> playing.start()).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void move() {
		ChessGameState state = playing.move(Position.of("a2"), Position.of("a4"));
		assertThat(state).isInstanceOf(Playing.class);
	}

	@Test
	@DisplayName("왕이 죽은 경우 정상적으로 상태가 변경되는지 테스트합니다.")
	void isKingDead() {
		playing.move(Position.of("c2"), Position.of("c4"));
		playing.move(Position.of("b7"), Position.of("b5"));
		playing.move(Position.of("c4"), Position.of("c5"));
		playing.move(Position.of("b5"), Position.of("b4"));
		playing.move(Position.of("c5"), Position.of("c6"));
		playing.move(Position.of("b4"), Position.of("b3"));
		playing.move(Position.of("c6"), Position.of("d7"));
		playing.move(Position.of("b3"), Position.of("a2"));
		ChessGameState state = playing.move(Position.of("d7"), Position.of("e8"));
		assertThat(state).isInstanceOf(Finish.class);
	}

	@Test
	void end() {
		ChessGameState state = playing.end();
		assertThat(state).isInstanceOf(Finish.class);
	}

	@Test
	void board() {
		assertThat(playing.board()).isInstanceOf(Board.class);
	}

	@Test
	void score() {
		assertThat(new Status(playing.score(Team.WHITE), (playing.score(Team.BLACK))).winner())
			.isEqualTo(Team.NONE);
	}

	@Test
	void turn() {
		assertThat(playing.turn().getTeam()).isEqualTo(Team.WHITE);
		playing.move(Position.of("a2"), Position.of("a4"));
		assertThat(playing.turn().getTeam()).isEqualTo(Team.BLACK);
	}
}