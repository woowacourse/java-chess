package domain.state;

public class Running implements State {
    @Override
    public boolean isRunning() {
        return true;
    }
}
