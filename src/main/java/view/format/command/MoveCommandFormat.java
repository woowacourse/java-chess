package view.format.command;

public class MoveCommandFormat extends PlayCommandFormat {

    private final String sourceInput;
    private final String targetInput;

    public MoveCommandFormat(final String sourceInput, final String targetInput) {
        super(PlayCommandFormatType.MOVE);
        this.sourceInput = sourceInput;
        this.targetInput = targetInput;
    }

    @Override
    public String getSourceInput() {
        return sourceInput;
    }

    @Override
    public String getTargetInput() {
        return targetInput;
    }
}
