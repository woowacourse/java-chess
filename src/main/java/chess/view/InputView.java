package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

public class InputView {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    
    private InputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }
    
    public static ProcessCommand inputProcessCommand() {
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
