package chess.web;

public class MoveResponseDto {

    private final String source;
    private final String destination;
    private final boolean finished;

    public MoveResponseDto(String source, String destination, boolean finished) {
        this.source = source;
        this.destination = destination;
        this.finished = finished;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isFinished() {
        return finished;
    }
}
