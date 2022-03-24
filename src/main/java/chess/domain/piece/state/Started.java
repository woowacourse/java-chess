package chess.domain.piece.state;

public abstract class Started implements State{
    @Override
    public State killed() {
        return new Dead();
    }
}
