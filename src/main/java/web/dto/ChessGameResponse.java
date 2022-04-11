package web.dto;

import java.util.Objects;

public class ChessGameResponse {

    private Long id;
    private String name;
    private String turn;

    public ChessGameResponse(Long id, String name, String turn) {
        this.id = id;
        this.name = name;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGameResponse that = (ChessGameResponse) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(),
            that.getName()) && Objects.equals(getTurn(), that.getTurn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTurn());
    }
}
