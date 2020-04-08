package chess.domain;

import chess.view.OutputView;

import java.util.Arrays;
import java.util.Objects;

public class MoveState {

    private Player player;
    private Square source;
    private Square target;
    private MoveCount moveCount;

    private static final String BLANK = " ";

    public MoveState(Player player) {
        this.player = player;
        this.moveCount = new MoveCount();
    }

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

    public void move(Square source, Square target, ChessBoard chessBoard) {
        try {
            if (!chessBoard.movePiece(source, target)) {
                OutputView.printCanNotMoveMessage();
            }
        } catch (Exception e) {
            OutputView.printInputError();
            throw e;
        }
        this.source = source;
        this.target = target;
        moveCount.setMoveCount();
    }

    public void move(String inputState, ChessBoard chessBoard) {
        Square source;
        Square target;
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
        this.source = source;
        this.target = target;
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

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveState moveState = (MoveState) o;
        return Objects.equals(player, moveState.player) &&
                Objects.equals(source, moveState.source) &&
                Objects.equals(target, moveState.target) &&
                Objects.equals(moveCount, moveState.moveCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, source, target, moveCount);
    }
}
