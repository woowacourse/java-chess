package chess.model.status;

public class End implements Status {

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String name() {
        return "end";
    }
}
