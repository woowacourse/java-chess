package chess.progress;

public enum Progress {
    STATUS(true),
    CONTINUE(true),
    ERROR(true),
    END(false);

    boolean isContinue;

    Progress(boolean isContinue) {
        this.isContinue = isContinue;
    }

    public boolean isNotEnd() {
        return this != END;
    }

    public boolean isError() {
        return this == ERROR;
    }
}
