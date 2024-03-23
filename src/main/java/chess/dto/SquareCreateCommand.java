package chess.dto;

public class SquareCreateCommand {

    private final String fileCommand;
    private final String rankValue;

    public SquareCreateCommand(String command) {
        this.fileCommand = String.valueOf(command.charAt(0));
        this.rankValue = String.valueOf(command.charAt(1));
    }

    public String getFileCommand() {
        return fileCommand;
    }

    public String getRankValue() {
        return rankValue;
    }
}
