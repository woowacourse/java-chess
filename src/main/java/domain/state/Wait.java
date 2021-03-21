package domain.state;

public class Wait implements State {
    @Override
    public boolean isRunning() {
        return true;
    }
}
