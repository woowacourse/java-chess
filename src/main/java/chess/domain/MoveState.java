package chess.domain;

import chess.view.OutputView;

import java.util.Arrays;

public class MoveState {

    private Square source;
    private Square target;
    private MoveCount moveCount = new MoveCount();

    private static final String BLANK = " ";

    public boolean isMove(String input) {
        return validateMoveState(input) == GameState.MOVE;
    }

    public GameState validateMoveState(String input) {
        if (GameState.MOVE.equals(splitToState(input))) {
            return GameState.MOVE;
        }
        return GameState.ERROR;
    }

    private GameState splitToState(String input) {
        String state = Arrays.asList(input.split(BLANK)).get(0);
        return GameState.of(state);
    }

    public void move(String inputState, ChessBoard chessBoard) {
        try {
            source = Square.of(Arrays.asList(inputState.split(BLANK)).get(1));
            target = Square.of(Arrays.asList(inputState.split(BLANK)).get(2));
            if (!chessBoard.movePiece(source, target)) {
                OutputView.printCanNotMoveMessage();
                return;
            }
        } catch (Exception e) {
            OutputView.printInputError();
            return;
        }
        moveCount.setMoveCount();
    }

    public Square getSource() {
        return source;
    }

    public Square getTarget() {
        return target;
    }

    public MoveCount getMoveCount() {
        return moveCount;
    }

}
