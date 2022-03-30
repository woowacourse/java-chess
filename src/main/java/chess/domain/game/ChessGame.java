package chess.domain.game;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.state.End;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Running;
import chess.domain.game.state.Waiting;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class ChessGame {

    private GameState state;

    public ChessGame() {
        state = new Waiting();
    }

    public void start() {
        Board board = new Board(BoardFactory.getInitialPieces());
        state = new Running(board);
    }

    public void movePiece(String movePositionInformation) {
        List<String> information = Arrays.asList(movePositionInformation.split(" "));
        state = state.movePiece(Position.valueOf(information.get(1)), Position.valueOf(information.get(2)));
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

    public Board getBoard() {
        return state.board();
    }
}
