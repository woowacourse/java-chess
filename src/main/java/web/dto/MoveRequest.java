package web.dto;

public class MoveRequest {

    private final Long id;
    private final String from;
    private final String to;

    public MoveRequest(Long id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }
}
