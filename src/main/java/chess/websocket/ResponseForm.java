package chess.websocket;

public class ResponseForm<T> {
    public static enum Form{
        MESSAGE,
        ENTER,
        PIECES,
        STATUS,
        MOVE,
        COLOR,
        ERROR
    }

    private String form;
    private T data;

    public ResponseForm(Form form, T data) {
        this.form = form.name();
        this.data = data;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
