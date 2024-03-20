package view.mapper;

import domain.position.File;
import java.util.Arrays;

public enum FileInput {

    A(File.A, "a"),
    B(File.B, "b"),
    C(File.C, "c"),
    D(File.D, "d"),
    E(File.E, "e"),
    F(File.F, "f"),
    G(File.G, "g"),
    H(File.H, "h");

    private final File file;
    private final String input;

    FileInput(File file, String input) {
        this.file = file;
        this.input = input;
    }

    public static File asFile(String input) {
        return Arrays.stream(values())
                .filter(fileInput -> fileInput.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 위치를 입력해주세요."))
                .file;
    }
}
