package domain.state;

import domain.command.CommandType;
import domain.command.MoveCommandTokens;
import domain.state.exceptions.StateException;
import domain.pieces.Piece;
import domain.pieces.Pieces;

import java.util.Objects;
import java.util.Set;

public abstract class GameState implements State {
    protected final StateType stateType;
    protected final Pieces pieces;

    protected GameState(final StateType stateType, final Pieces pieces) {
        this.stateType = stateType;
        this.pieces = pieces;
    }

    protected abstract State start();

    protected abstract State report();

    protected abstract State end();

    protected abstract State move(String from, String to);

    @Override
    public final State pushCommend(String input) {
        CommandType commandType = CommandType.getInstance(input);

        if (commandType == CommandType.END) {
            return end();
        }
        if (commandType == CommandType.START) {
            return start();
        }
        if (commandType == CommandType.STATUS) {
            return report();
        }
        if (commandType == CommandType.MOVE) {
            MoveCommandTokens moveCommandTokens = MoveCommandTokens.of(input);
            return move(moveCommandTokens.getSource(), moveCommandTokens.getDestination());
        }

        throw new StateException("올바른 명령어가 아닙니다.");
    }

    @Override
    public boolean isReported() {
        return this instanceof Reported;
    }

    @Override
    public boolean isEnded() {
        return this instanceof Ended;
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
    public String getStateName() {
        return stateType.name();
    }

    @Override
    public Set<Piece> getSet() {
        return pieces.getSet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return Objects.equals(pieces, gameState.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "pieces=" + pieces +
                '}';
    }
}
