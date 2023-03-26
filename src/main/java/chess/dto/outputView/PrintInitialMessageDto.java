package chess.dto.outputView;

public final class PrintInitialMessageDto {

    private final String message;

    public PrintInitialMessageDto() {
        this.message = "> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n";
    }

    public String getMessage() {
        return message;
    }
}
