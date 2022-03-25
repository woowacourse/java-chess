package chess.domain.game;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Running;
import java.util.Arrays;
import java.util.List;

public class ChessGame {

    private GameState state;
    private final Board board;

    public ChessGame() {
        state = new Running();
        board = new Board(BoardFactory.getInitialPieces());
    }

    public void movePiece(String movePositionInformation) {
        List<String> information = Arrays.asList(movePositionInformation.split(" "));
        state = state.movePiece(board, Position.valueOf(information.get(1)),
            Position.valueOf(information.get(2)));
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public Board getBoard() {
        return board;
    }
}
