package chess.web.utils;

import java.util.Arrays;

import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.Piece;

public class Converter {
    public static Position positionFrom(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("position 정보가 잘못되었습니다.");
        }

        File file = Arrays.stream(File.values())
            .filter(it -> it.name().equals(String.valueOf(position.charAt(0))))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("position 정보가 잘못되었습니다."));

        Rank rank = Arrays.stream(Rank.values())
            .filter(it -> it.getValue() == Integer.parseInt(String.valueOf(position.charAt(1))))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("position 정보가 잘못되었습니다."));

        return Position.of(file, rank);
    }

    public static String imagePathFrom(Piece piece) {
        String name = piece.getName().toLowerCase();
        String color = piece.getColor().toLowerCase();
        return "images/" + name + "_" + color + ".png";
    }

    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
