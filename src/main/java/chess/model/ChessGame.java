package chess.model;

import static java.util.stream.Collectors.toList;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private final Turn turn;

    public ChessGame() {
        this.board = Board.create();
        this.turn = new Turn();
    }

    public void move(final Position source, final Position target) {
        board.move(source, target, turn.findCurrentPlayer());
        turn.next();
    }

    public boolean isGameEnd() {
        return !board.findKing(turn.findCurrentPlayer());
    }

    public Scores calculateScoreAll() {
        final List<Score> scores = turn.allPlayers().stream()
                .map(board::calculateScore)
                .collect(toList());
        return new Scores(scores);
    }

    public Map<Position, Piece> getBoard() {
        return board.getSquares();
    }
}
