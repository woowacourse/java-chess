package chess.result;

public class Result {
    private boolean success;
    private Object object;

    public Result(final boolean success, final Object object) {
        this.success = success;
        this.object = object;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getObject() {
        return object;
    }
}
