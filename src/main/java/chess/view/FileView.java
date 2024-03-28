package chess.view;

import chess.domain.position.File;

import java.util.Arrays;

public enum FileView {
    A("a", File.A),
    B("b", File.B),
    C("c", File.C),
    D("d", File.D),
    E("e", File.E),
    F("f", File.F),
    G("g", File.G),
    H("h", File.H);

    private final String text;
    private final File file;

    FileView(String text, File file) {
        this.text = text;
        this.file = file;
    }

    public static File from(String fileText) {
        return Arrays.stream(values())
                .filter(fileView -> fileText.equals(fileView.text))
                .map(fileView -> fileView.file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File은 a~h 중 하나이어야 합니다."));
    }
}
