package chess.dto.outputView;

public final class PrintInitialMessageDto {

    private final String message;

    public PrintInitialMessageDto() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("> 체스 게임을 시작합니다.\n");
        stringBuilder.append("> 게임 시작 : start\n");
        stringBuilder.append("> 게임 종료 : end\n");
        stringBuilder.append("> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n");
        this.message = stringBuilder.toString();
    }

    public String getMessage() {
        return message;
    }
}
