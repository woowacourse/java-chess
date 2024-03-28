package view.format.command;

public class EndCommandFormat extends PlayCommandFormat {

    public EndCommandFormat() {
        super(PlayCommandFormatType.END);
    }

    @Override
    public String getSourceInput() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }

    @Override
    public String getTargetInput() {
        throw new UnsupportedOperationException("사용할 수 없는 기능입니다.");
    }
}
