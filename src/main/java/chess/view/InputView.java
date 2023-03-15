package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

public class InputView {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    
    public static ProcessCommand inputProcessCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        try {
            return ProcessCommand.from(bufferedReader.readLine());
        } catch (IOException e) {
            return inputProcessCommand();
        }
    }
    
    public static <T> T repeat(Supplier<T> inputProcess) {
        try {
            return inputProcess.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return repeat(inputProcess);
        }
    }
}
