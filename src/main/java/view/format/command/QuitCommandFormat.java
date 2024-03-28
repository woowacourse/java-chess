package view.format.command;

public class QuitCommandFormat extends PlayCommandFormat {

    public QuitCommandFormat() {
        super(PlayCommandFormatType.QUIT);
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
