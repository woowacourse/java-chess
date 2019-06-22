package chess.dto;

import java.util.Objects;

public class RoomDto {

    private long id;
    private boolean status;
    private String winner;

    public void setId(final long id) {
        this.id = id;
    }

    public void setStatus(final boolean status) {
        this.status = status;
    }

    public void setWinner(final String winner) {
        this.winner = winner;
    }

    public long getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RoomDto roomDto = (RoomDto) o;
        return id == roomDto.id &&
                status == roomDto.status &&
                Objects.equals(winner, roomDto.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, winner);
    }
}
