package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.List;
import java.util.Map;

public final class Board {

    private State state;
    private final Players players;

    public Board() {
        this(new Ready(), new Players("player1", "player2"));
    }

    public Board(String whitePlayer, String blackPlayer) {
        this(new Ready(), new Players(whitePlayer, blackPlayer));
    }

    public Board(final State state, final Players players) {
        this.state = state;
        this.players = players;
    }

    public void movePiece(final String sourceValue, final String targetValue) {
        Position sourcePosition = Position.of(sourceValue);
        Position targetPosition = Position.of(targetValue);
        state = state.movePiece(sourcePosition, targetPosition);
    }

    public void init() {
        state = new Ready().init();
    }

    public double score(final Color color) {
        return state.score(color);
    }

    public Color winner() {
        return state.winner();
    }

    public Map<Position, Piece> pieces() {
        return state.pieces();
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public Color turn() {
        return state.turn();
    }

    public List<Position> movablePositions(String source) {
        return state.movablePositions(source);
    }

    public Players players() {
        return players;
    }
}
