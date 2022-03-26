package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Position;
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
		state = state.proceed(new Move("b2", "b3"));

		assertThat(state).isInstanceOf(RunningBlackTurn.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 Move 커맨드로 움직인다.")
	void movePiece() {
		Map<Position, Piece> board = Map.of(new Position(2, 2), Pawn.createWhite());
		State state = State.create(board)
			.proceed(new Start())
			.proceed(new Move("b2", "b3"));

		assertThat(state.getBoard().findPiece(new Position(3, 2)).get())
			.isInstanceOf(Pawn.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 Black을 움직일 수 없다.")
	void movePieceException() {
		Map<Position, Piece> board = Map.of(new Position(7, 2), Pawn.createBlack());
		State state = State.create(board)
			.proceed(new Start());

		assertThatThrownBy(() -> state.proceed(new Move("b7", "b6")))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
