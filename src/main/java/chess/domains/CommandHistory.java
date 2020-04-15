package chess.domains;

public class CommandHistory {
    private String commandHistory;
    private String errorMsg;
    private String source;
    private String target;

    public CommandHistory(String commandHistory, String source, String target, String errorMsg) {
        this.commandHistory = commandHistory;
        this.source = source;
        this.target = target;
        this.errorMsg = errorMsg;
    }

    public String getCommandHistory() {
        return commandHistory;
    }

    public void setCommandHistory(String commandHistory) {
        this.commandHistory = commandHistory;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
