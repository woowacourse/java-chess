package domain.commend;

import domain.commend.exceptions.StateException;
import domain.pieces.Piece;
import domain.pieces.Pieces;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class GameState implements State {
    private static final int FIRST_TOKEN_INDEX = 0;
    private static final int SECOND_TOKEN_INDEX = 1;
    private static final int THIRD_TOKEN_INDEX = 2;
    private static final int MOVE_COMMEND_SIZE = 3;

    protected Pieces pieces;

    protected GameState(Pieces pieces) {
        this.pieces = pieces;
    }

    @Override
    public final State pushCommend(String input) {
        if (input.equals("end")) {
            return end();
        }
        if (input.equals("start")) {
            return start();
        }
        if (input.equals("status")) {
            return status();
        }
        return parseAndPushCommend(input);
    }

    private State parseAndPushCommend(String input) {
        List<String> tokens = Arrays.asList(input.split(" "));
        if (tokens.get(FIRST_TOKEN_INDEX).equals("move")) {
            validateTokenSizeIsMoveCommendSize(tokens);
            return move(tokens.get(SECOND_TOKEN_INDEX), tokens.get(THIRD_TOKEN_INDEX));
        }

        throw new StateException("올바른 명령어가 아닙니다.");
    }

    private void validateTokenSizeIsMoveCommendSize(List<String> tokens) {
        if (tokens.size() != MOVE_COMMEND_SIZE) {
            throw new StateException("잘못된 명령어입니다.");
        }
    }

    @Override
    public boolean isStatus() {
        return this instanceof Status;
    }

    @Override
    public boolean isPlaying() {
        return this instanceof Playing;
    }

    @Override
    public Pieces getPieces() {
        return new Pieces(pieces.getSet());
    }

    @Override
    public Set<Piece> getSet() {
        return pieces.getSet();
    }
}
