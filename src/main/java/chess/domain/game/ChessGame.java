package chess.domain.game;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Piece;

public class ChessGame {

	private State state;

	public ChessGame() {
		state = new Start(Board.create());
	}

	public void start() {
		state = state.start();
	}

    public void move(String from, String to) {
        move(Coordinate.of(from), Coordinate.of(to));
    }

	public void move(Coordinate from, Coordinate to) {
		state = state.move(from, to);
	}

	public void end() {
		state = state.end();
	}

    public List<String> getPieces() {
        return state.getValue()
            .values()
            .stream()
            .map(piece -> piece.getTeam().getName() + "-" + piece.getSymbol().toUpperCase())
            .collect(Collectors.toList());
    }

	public boolean isFinished() {
		return state.isFinished();
	}
	
	public Map<Coordinate, Piece> getValue() {
		return state.getValue();
	}
}
