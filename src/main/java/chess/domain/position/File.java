package chess.domain.position;

import java.util.Arrays;

public enum File {

    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    public static final int FILES_TOTAL_SIZE = 8;

    public static final String INVALID_FILE_EXCEPTION_MESSAGE = "유효하지 않은 파일입니다. (파일은 a~h까지 입력 가능합니다.)";
    public static final String INVALID_FILE_INDEX_EXCEPTION_MESSAGE = "유효하지 않은 파일 인덱스입니다. (파일 인덱스는  0~7까지 입력 가능합니다.)";

    private final String rawFile;
    private final int fileIdx;

    File(String file, int fileIdx) {
        this.rawFile = file;
        this.fileIdx = fileIdx;
    }

    public static File from(String value) {
        return Arrays.stream(values())
            .filter(file -> file.rawFile.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_EXCEPTION_MESSAGE));
    }

    public static File of(int valueIdx) {
        return Arrays.stream(values())
            .filter(file -> file.fileIdx == valueIdx)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_INDEX_EXCEPTION_MESSAGE));
    }

    public boolean hasSameFileIdx(File another) {
        return this == another;
    }


    public int rawDifference(File targetFile) {
        return targetFile.fileIdx - this.fileIdx;
    }

    public String getRawFile() {
        return rawFile;
    }

    public int getFileIdx() {
        return fileIdx;
    }

    @Override
    public String toString() {
        return "File{" +
            "rawFile='" + rawFile + '\'' +
            ", fileIdx=" + fileIdx +
            '}';
    }

}
