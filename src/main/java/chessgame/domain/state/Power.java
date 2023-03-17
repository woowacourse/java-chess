package chessgame.domain.state;

import chessgame.domain.Command;

public class Power {
    private State state = new End();

    public void changeState(Command command) {
        state.click(this, command);
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isStart() {
        return state instanceof Start;
    }
}
