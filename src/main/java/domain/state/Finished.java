package domain.state;

import domain.exception.InvalidMenuException;

public class Finished implements State {
    @Override
    public boolean isRunning() {
        return false;
    }
}
