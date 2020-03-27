package domain.commend;

import domain.commend.exceptions.StateException;
import domain.pieces.Piece;
import domain.pieces.Pieces;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class GameState implements State {
    private Pieces pieces;

    protected GameState(Pieces pieces) {
        this.pieces = pieces;
    }

    protected Pieces getPieces() {
        return pieces;
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
        if (tokens.get(0).equals("move")) {
            validateTokensSize(tokens);
            return move(tokens.get(1), tokens.get(2));
        }

        throw new StateException("올바른 명령어가 아닙니다.");
    }

    private void validateTokensSize(List<String> tokens) {
        if (tokens.size() != 3) {
            throw new StateException("잘못된 명령어입니다.");
        }
    }

    @Override
    public Set<Piece> getSet() {
        return pieces.getSet();
    }
}
