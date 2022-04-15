package chess.model.status;

public class Running implements Status {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String name() {
        return "running";
    }
}
