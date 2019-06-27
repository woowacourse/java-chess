package chess.application.dto;

public class ChessLogDto {
    private String turn;
    private String source;
    private String target;

    public ChessLogDto(String turn, String source, String target) {
        this.turn = turn;
        this.source = source;
        this.target = target;
    }

    public String getTurn() {
        return turn;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "ChessLogDto{" +
                "turn='" + turn + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
