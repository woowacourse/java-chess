package controller.mapper;

import domain.piece.File;
import java.util.Arrays;


public enum FileMapper {
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

    FileMapper(String text, File file) {
        this.text = text;
        this.file = file;
    }

    public static File convertTextToFile(String text) {
        return Arrays.stream(FileMapper.values())
                .filter(fileMapper -> fileMapper.text.equals(text))
                .map(FileMapper::getFile)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판의 가로 좌표는 a~h의 범위를 가집니다. 범위 안의 문자를 입력해주세요."));
    }

    private File getFile() {
        return this.file;
    }
}
