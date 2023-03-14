package chess.domain.piece.position;

import java.util.regex.Pattern;

public class File {

    private static final Pattern pattern = Pattern.compile("^[a-h]$");
    private final char file;

    private File(final char file) {
        validate(file);
        this.file = file;
    }

    private void validate(final char file) {
        if (!pattern.matcher(String.valueOf(file)).matches()) {
            throw new IllegalArgumentException("a 에서 h 사이의 문자만 들어올 수 있습니다.");
        }
    }

    public static File from(final char file) {
        return new File(file);
    }
}
