package chess.domain.game;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.game.state.End;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Running;
import chess.domain.game.state.Waiting;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessGame {

    private GameState state;

    public ChessGame() {
        state = new Waiting();
    }

    public ChessGame(final GameState gameState) {
        state = gameState;
    }

    public void start(final Map<Position, Piece> pieces, final Color color) {
        final Board board = new Board(pieces);
        state = new Running(board, color);
    }

    public void movePiece(final String source, final String target) {
        state = state.movePiece(Position.valueOf(source), Position.valueOf(target));
    }

    public void end() {
        if (state.isWaiting()) {
            state = new End(new Board());
            return;
        }
        state = new End(state.board());
    }

    public boolean isWaiting() {
        return state.isWaiting();
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public Map<Color, Double> calculateScore() {
        return state.calculateScore();
    }

    public Color judgeWinner() {
        return state.getWinTeamColor();
    }

    public Color getTurn() {
        return state.getTurn();
    }

    public Board getBoard() {
        return state.board();
    }

    public String getState() {
        return state.representative();
    }
}
