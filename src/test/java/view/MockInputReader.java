package view;

import java.util.Arrays;
import java.util.Iterator;

public class MockInputReader implements InputReader {
    private final Iterator<String> inputs;

    public MockInputReader(String... inputs) {
        this.inputs = Arrays.stream(inputs).iterator();
    }

    @Override
    public String readInput() {
        if (inputs.hasNext()) {
            return inputs.next();
        }

        throw new IllegalArgumentException("모킹할 입력값이 더이상 없습니다.");
    }
}
