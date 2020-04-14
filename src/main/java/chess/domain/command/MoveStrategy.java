package chess.domain.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.position.Position;

public class MoveStrategy implements CommandStrategy {
	@Override
	public ChessGame execute(List<String> splitedInput, ChessGame chessGame) {
		Position sourcePosition = new Position(splitedInput.get(1));
		Position targetPosition = new Position(splitedInput.get(2));
		chessGame.move(sourcePosition, targetPosition);
		return chessGame;
	}
}
