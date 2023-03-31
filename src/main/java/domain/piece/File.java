package domain.piece;

import java.util.Arrays;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String text;
    private final int order;

    File(String text, int order) {
        this.text = text;
        this.order = order;
    }

    public static File from(String fileText) {
        return Arrays.stream(File.values())
                .filter(file -> file.getText().equals(fileText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file입니다."));
    }

    public File nextFile() {
        return Arrays.stream(File.values())
                .filter(file -> file.order == this.order + 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("인덱스를 벗어난 움직임입니다."));
    }

    public File previousFile() {
        return Arrays.stream(File.values())
                .filter(file -> file.order == this.order - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("인덱스를 벗어난 움직임입니다."));
    }

    public String getText() {
        return text;
    }

    public int calculateIncrement(File targetFile) {
        return targetFile.order - this.order;
    }
}
