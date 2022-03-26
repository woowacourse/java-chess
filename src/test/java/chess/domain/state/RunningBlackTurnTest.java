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
public class RunningBlackTurnTest {

	@Test
	@DisplayName("RunningBlackTurn 상태에서 Move 커맨드를 받으면 RunningWhiteTurn 상태가 된다.")
	void blackTurn_to_whiteTurn() {
		State state = State.create()
			.proceed(new Start())
			.proceed(new Move("b2", "b3"))
			.proceed(new Move("b7", "b6"));

		assertThat(state).isInstanceOf(RunningWhiteTurn.class);
	}

	@Test
	@DisplayName("RunningBlackTurn일 때 Move 커맨드로 움직인다.")
	void movePiece() {
		Map<Position, Piece> board = Map.of(new Position(7, 2), Pawn.createBlack(),
			new Position(2, 2), Pawn.createWhite());
		State state = State.create(board)
			.proceed(new Start())
			.proceed(new Move("b2", "b3"))
			.proceed(new Move("b7", "b6"));

		assertThat(state.getBoard().findPiece(new Position(6, 2)).get())
			.isInstanceOf(Pawn.class);
	}

	@Test
	@DisplayName("RunningBlackTurn일 때 White을 움직일 수 없다.")
	void movePieceException() {
		Map<Position, Piece> board = Map.of(new Position(2, 2), Pawn.createWhite());
		State state = State.create(board)
			.proceed(new Start())
			.proceed(new Move("b2", "b3"));

		assertThatThrownBy(() -> state.proceed(new Move("b3", "b4")))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
