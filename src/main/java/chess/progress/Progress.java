package chess.progress;

public enum Progress {
    CONTINUE(true),
    ERROR(true),
    END(false);

    boolean isContinue;

    Progress(boolean isContinue) {
        this.isContinue = isContinue;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isError() {
        return this == ERROR;
    }
}
