package chess.util;

import chess.view.OutputView;

import java.util.function.Supplier;

public class Repeater {
    private Repeater() {
    }

    public static <T> T repeatOnError(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {  // TODO 사용자 정의 예외만 잡도록 리팩토링
            OutputView.printMessage(e.getMessage());
            return repeatOnError(supplier);
        }
    }
}
