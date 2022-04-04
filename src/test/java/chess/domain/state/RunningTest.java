package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import chess.domain.command.MoveCommand;
import chess.domain.command.SingleCommand;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

	@Test
	@DisplayName("Running 상태에서 Start 커맨드를 받을 수 없다")
	void running_cannot_start() {
		State state = State.create()
			.proceed(SingleCommand.START);
		assertThatThrownBy(() -> state.proceed(SingleCommand.START))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("Running 상태에서 end 커맨드를 받으면 Finished이다.")
	void running_end() {
		State state = State.create()
			.proceed(SingleCommand.START);

		assertThat(state.proceed(SingleCommand.END))
			.isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn 상태에서 MoveCommand 커맨드를 받으면 RunningBlackTurn 상태가 된다.")
	void blackTurn() {
		State state = State.create();
		state = state.proceed(SingleCommand.START);
		state = state.proceed(
				new MoveCommand(new Position(2, 2), new Position(3, 2)));

		assertThat(state.isWhite()).isFalse();
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 MoveCommand 커맨드로 움직인다.")
	void movePiece_in_white() {
		Map<Position, Piece> board = Map.of(new Position(2, 2), Pawn.createWhite());
		State state = State.create(board)
				.proceed(SingleCommand.START)
				.proceed(
						new MoveCommand(new Position(2, 2), new Position(3, 2)));

		assertThat(state.getBoard().findPiece(new Position(3, 2)))
				.isInstanceOf(Pawn.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 Black을 움직일 수 없다.")
	void movePieceException_in_white() {
		Map<Position, Piece> board = Map.of(new Position(7, 2), Pawn.createBlack());
		State state = State.create(board)
				.proceed(SingleCommand.START);

		assertThatThrownBy(() -> state.proceed(
				new MoveCommand(new Position(7, 2), new Position(6, 2))))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 상대방의 King을 잡으면 Finished로 전환")
	void killOpponentKing_in_white() {
		Map<Position, Piece> board = Map.of(
				new Position(6, 1), King.createBlack(),
				new Position(7, 3), King.createWhite(),
				new Position(5, 2), Pawn.createWhite()
		);
		State state = State.create(board);
		state = state.proceed(SingleCommand.START);
		state = state.proceed(
				new MoveCommand(new Position(5, 2), new Position(6, 1)));

		assertThat(state).isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("RunningWhiteTurn일 때 Staus 커맨드이면 자기 자신을 유지해야 한다")
	void statusCommand_in_white() {
		State state = State.create();
		state = state.proceed(SingleCommand.START);
		state = state.proceed(SingleCommand.STATUS);

		assertThat(state.isWhite()).isTrue();
	}

	@Test
	@DisplayName("RunningBlackTurn 상태에서 MoveCommand 커맨드를 받으면 RunningWhiteTurn 상태가 된다.")
	void whiteTurn() {
		State state = State.create();
		state = state.proceed(SingleCommand.START);
		state = state.proceed(
				new MoveCommand(new Position(2, 2), new Position(3, 2)));
		state = state.proceed(
				new MoveCommand(new Position(7, 2), new Position(6, 2)));

		assertThat(state.isWhite()).isTrue();
	}

	@Test
	@DisplayName("RunningBlackTurn일 때 MoveCommand 커맨드로 움직인다.")
	void movePiece_in_black() {
		Map<Position, Piece> board = Map.of(
				new Position(7, 2), King.createBlack(),
				new Position(2, 2), King.createWhite());
		State state = State.create(board)
				.proceed(SingleCommand.START)
				.proceed(
						new MoveCommand(new Position(2, 2), new Position(3, 2)))
				.proceed(
						new MoveCommand(new Position(7, 2), new Position(6, 2)));

		assertThat(state.getBoard().findPiece(new Position(6, 2)))
				.isInstanceOf(King.class);
	}

	@Test
	@DisplayName("RunningBlackTurn일 때 White을 움직일 수 없다.")
	void movePieceException_in_black() {
		Map<Position, Piece> board = Map.of(
				new Position(2, 2), King.createWhite(),
				new Position(8, 2), King.createBlack());
		State state = State.create(board)
				.proceed(SingleCommand.START)
				.proceed(new MoveCommand(new Position(2, 2), new Position(3, 2)));

		assertThatThrownBy(() -> state.proceed(
				new MoveCommand(new Position(3, 2), new Position(4, 2))))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("RunningBlackTurn일 때 상대방의 King을 잡으면 Finished로 전환")
	void killOpponentKing_in_black() {
		Map<Position, Piece> board = Map.of(
				new Position(6, 2), King.createWhite(),
				new Position(7, 3), King.createBlack(),
				new Position(7, 2), Pawn.createBlack()
		);
		State state = State.create(board);
		state = state.proceed(SingleCommand.START);
		state = state.proceed(
				new MoveCommand(new Position(6, 2), new Position(6, 3)));
		state = state.proceed(
				new MoveCommand(new Position(7, 2), new Position(6, 3)));

		assertThat(state).isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("RunningBlackTurn일 때 Staus 커맨드이면 자기 자신을 유지해야 한다")
	void statusCommand_in_black() {
		State state = State.create();
		state = state.proceed(SingleCommand.START);
		state = state.proceed(
				new MoveCommand(new Position(2, 2), new Position(3, 2)));
		state = state.proceed(SingleCommand.STATUS);

		assertThat(state.isWhite()).isFalse();
	}
}
