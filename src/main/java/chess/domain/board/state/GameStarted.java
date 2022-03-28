package chess.domain.board.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GameStarted implements GameState {

    protected final Board board;

    public GameStarted(Board board) {
        this.board = board;
    }

    @Override
    public double calculateBlackScore() {
        return board.calculateBlackScore();
    }

    @Override
    public double calculateWhiteScore() {
        return board.calculateWhiteScore();
    }

    @Override
    public Rank getRank(int rankLine) {
        return board.getRank(rankLine);
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
