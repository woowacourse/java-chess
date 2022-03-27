package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.command.Status;
import chess.domain.piece.King;
import chess.domain.position.Position;
import chess.domain.command.Move;
import chess.domain.command.Start;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class RunningWhiteTurnTest {

	@Test
	@DisplayName("RunningWhiteTurn 상태에서 Move 커맨드를 받으면 RunningBlackTurn 상태가 된다.")
	void blackTurn() {
		State state = State.create();
		state = state.proceed(new Start());
		state = state.proceed(
			new Move(new Position(2, 2), new Position(3, 2)));

		assertThat(state).isInstanceOf(RunningBlackTurn.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 Move 커맨드로 움직인다.")
	void movePiece() {
		Map<Position, Piece> board = Map.of(new Position(2, 2), Pawn.createWhite());
		State state = State.create(board)
			.proceed(new Start())
			.proceed(
				new Move(new Position(2, 2), new Position(3, 2)));

		assertThat(state.getBoard().findPiece(new Position(3, 2)).get())
			.isInstanceOf(Pawn.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 Black을 움직일 수 없다.")
	void movePieceException() {
		Map<Position, Piece> board = Map.of(new Position(7, 2), Pawn.createBlack());
		State state = State.create(board)
			.proceed(new Start());

		assertThatThrownBy(() -> state.proceed(
			new Move(new Position(7, 2), new Position(6, 2))))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 상대방의 King을 잡으면 Finished로 전환")
	void killOpponentKing() {
		Map<Position, Piece> board = Map.of(
			new Position(6, 1), King.createBlack(),
			new Position(7, 3), King.createWhite(),
			new Position(5, 2), Pawn.createWhite()
		);
		State state = State.create(board);
		state = state.proceed(new Start());
		state = state.proceed(
			new Move(new Position(5, 2), new Position(6, 1)));

		assertThat(state).isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 Staus 커맨드이면 자기 자신을 유지해야 한다")
	void statusCommand() {
		State state = State.create();
		state = state.proceed(new Start());
		state = state.proceed(new Status());

		assertThat(state).isInstanceOf(RunningWhiteTurn.class);
	}
}
