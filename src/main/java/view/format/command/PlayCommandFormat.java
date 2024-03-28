package view.format.command;

public abstract class PlayCommandFormat {

    private final PlayCommandFormatType playCommandType;

    protected PlayCommandFormat(final PlayCommandFormatType playCommandType) {
        this.playCommandType = playCommandType;
    }

    public abstract String getSourceInput();

    public abstract String getTargetInput();

    public PlayCommandFormatType getPlayCommandType() {
        return playCommandType;
    }
}
