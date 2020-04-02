package chess.domain.command;

import java.util.List;

import chess.domain.ChessGame;

public interface CommandStrategy {
	ChessGame execute(List<String> splittedInput, ChessGame chessGame);
}
