package chess.model.status;

public class Ready implements Status {
    @Override
    public boolean isEnd() {
        return false;
    }
}
