package chess.domain.game;

import java.util.Arrays;
import java.util.List;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.game.state.End;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Running;
import chess.domain.game.state.Waiting;
import chess.domain.piece.Color;

public class ChessGame {

    private GameState state;
    private final Board board;

    public ChessGame() {
        state = new Waiting();
        board = new Board();
    }

    public void start() {
        state = new Running();
        board.placePieces(BoardFactory.getInitialPieces());
    }

    public void movePiece(String movePositionInformation) {
        List<String> information = Arrays.asList(movePositionInformation.split(" "));
        state = state.movePiece(board, Position.valueOf(information.get(1)), Position.valueOf(information.get(2)));
    }

    public void end() {
        state = new End();
    }

    public boolean isWaiting() {
        return state.isWaiting();
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public double calculateScore(Color color) {
        return state.calculateScore(board, color);
    }

    public Color judgeWinner() {
        return state.getWinTeamColor(board);
    }

    public Board getBoard() {
        return board;
    }
}
