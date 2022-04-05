package chess.dto;

public class CommandDto {
    String from;
    String to;

    public CommandDto(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public CommandDto(String commands) {
        this.from = commands.split(" ")[0];
        this.to = commands.split(" ")[1];
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "CommandDto{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
