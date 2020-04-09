package chess.web;

public class ChessCommand {
    String command;

    public ChessCommand(String command) {
        this.command = command;
    }

    public String get() {
        return command;
    }


}
