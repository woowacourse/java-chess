package chess.view;

public final class Input {

    private Input() {
    }

    public static void inputCommand(final Enterable enterable) {
        final String raw = enterable.enter();
        checkBlank(raw);
    }

    private static void checkBlank(final String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("값을 입력해주세요!");
        }
    }
}
