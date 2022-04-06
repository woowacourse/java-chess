package chess.domain;

import java.util.Map;

import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.GameState;

public class ChessGame {

	private static final int NAME_LIMIT_LENGTH = 20;
	private static final String INVALID_NAME_LENGTH = "이름은 20자 이하로 설정할 수 있습니다.";

	private final String name;
	private final GameState state;

	public ChessGame(String name, GameState state) {
		validateNameLength(name);
		this.name = name;
		this.state = state;
	}

	private void validateNameLength(String name) {
		if (name.length() > NAME_LIMIT_LENGTH) {
			throw new IllegalArgumentException(INVALID_NAME_LENGTH);
		}
	}

	public String getName() {
		return name;
	}

	public ChessGame execute(Command command) {
		GameState updatedState = state.proceed(command);
		return new ChessGame(this.name, updatedState);
	}

	public GameState getState() {
		return this.state;
	}

	public Map<Position, Piece> getBoard() {
		return this.state.getBoard();
	}

	public boolean isFinished() {
		return state.isFinished();
	}

	public boolean isReady() {
		return state.isReady();
	}

	public Piece getPieceByPosition(Position position) {
		return state.getByPosition(position);
	}
}
