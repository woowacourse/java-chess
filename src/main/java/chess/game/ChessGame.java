package chess.game;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.dto.SquareResponse;
import java.util.List;

public class ChessGame {
    private final Board board;

    public ChessGame() {
        this.board = new Board(BoardFactory.create());
    }

    public void move(Position source, Position target) {
        board.move(source, target);
    }

    public List<SquareResponse> getBoard() {
        return board.getBoard().entrySet().stream()
                .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                .collect(toList());
    }
}
