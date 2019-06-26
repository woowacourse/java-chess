package chess.dto;

import java.util.Objects;

public class RoomDto {

    private long id;
    private String status;
    private String winner;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(final String winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RoomDto roomDto = (RoomDto) o;
        return id == roomDto.id &&
                Objects.equals(status, roomDto.status) &&
                Objects.equals(winner, roomDto.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, winner);
    }
}
