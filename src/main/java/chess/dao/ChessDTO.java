package chess.dao;

import java.util.Objects;

public class ChessDTO {

    private Long id;
    private Long gameid;
    private String name;
    private String color;
    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameid() {
        return gameid;
    }

    public void setGameid(Long gameid) {
        this.gameid = gameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessDTO that = (ChessDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects
            .equals(getGameid(), that.getGameid()) && Objects
            .equals(getName(), that.getName()) && getColor() == that.getColor()
            && Objects.equals(getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGameid(), getName(), getColor(), getPosition());
    }

    @Override
    public String toString() {
        return "ChessDbDTO{" +
            "id=" + id +
            ", gameid=" + gameid +
            ", name='" + name + '\'' +
            ", teamColor=" + color +
            ", position='" + position + '\'' +
            '}';
    }
}
