package chess.controller.mapper;

import java.util.Arrays;

import chess.domain.position.File;

public class FileMapper {

    public static File map(String input) {
        return Arrays.stream(File.values())
                .filter(file -> file.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 파일입니다"));
    }
}
