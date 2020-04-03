package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.board.Square;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveState {

    private static final String Move = "move";
    private static final String BLANK = " ";

    public static boolean isMove(String input) {
        return validateMoveState(input) == GameState.MOVE;
    }

    public static GameState validateMoveState(String input) {
        if (GameState.MOVE.equals(splitToState(input))) {
            return GameState.MOVE;
        }
        return GameState.ERROR;
    }

    private static GameState splitToState(String input) {
        String state = Arrays.asList(input.split(BLANK)).get(0);
        return GameState.of(state);
    }

    public static void move(String inputState, ChessBoard chessBoard) {
        try {
            List<Square> moveSquare = new ArrayList<>();
            moveSquare.add(Square.of(Arrays.asList(inputState.split(BLANK)).get(1)));
            moveSquare.add(Square.of(Arrays.asList(inputState.split(BLANK)).get(2)));
            if (!chessBoard.movePiece(moveSquare)) {
                OutputView.printCanNotMoveMessage();
            }
        } catch (Exception e) {
            OutputView.printInputError();
        }
    }
}
