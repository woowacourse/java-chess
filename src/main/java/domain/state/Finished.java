package domain.state;

public class Finished implements State {
    @Override
    public boolean isRunning() {
        return false;
    }
}
