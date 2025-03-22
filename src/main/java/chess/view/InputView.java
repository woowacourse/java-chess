package chess.view;

import chess.domain.piece.Column;
import chess.domain.piece.Row;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static Entry<Column, Row> choiceWhitePiece() {
        System.out.println("움직일 백 기물을 선택해주세요 예) A,3");
        return choicePiece();
    }

    public static Entry<Column, Row> choiceBlackPiece() {
        System.out.println("움직일 흑 기물을 선택해주세요 예) A,3");
        return choicePiece();
    }

    public static Entry<Column, Row> choiceDestination() {
        System.out.println("움직이고 싶은 좌표를 입력해주세요 예) A,3");
        return choicePiece();
    }

    private static Entry<Column, Row> choicePiece() {
        String[] input = scanner.nextLine().split(",");
        return Map.entry(convertToColumn(input[0]), convertToRaw(input[1]));
    }

    private static Column convertToColumn(String input) {
        if (input.equals("A")) {
            return Column.A;
        }
        if (input.equals("B")) {
            return Column.B;
        }
        if (input.equals("C")) {
            return Column.C;
        }
        if (input.equals("D")) {
            return Column.D;
        }
        if (input.equals("E")) {
            return Column.E;
        }
        if (input.equals("F")) {
            return Column.F;
        }
        if (input.equals("G")) {
            return Column.G;
        }
        throw new IllegalArgumentException("좌표를 잘못 입력하셨습니다.");
    }

    private static Row convertToRaw(String input) {
        if (input.equals("1")) {
            return Row.ONE;
        }
        if (input.equals("2")) {
            return Row.TWO;
        }
        if (input.equals("3")) {
            return Row.THREE;
        }
        if (input.equals("4")) {
            return Row.FOUR;
        }
        if (input.equals("5")) {
            return Row.FIVE;
        }
        if (input.equals("6")) {
            return Row.SIX;
        }
        if (input.equals("7")) {
            return Row.SEVEN;
        }
        if (input.equals("8")) {
            return Row.EIGHT;
        }
        throw new IllegalArgumentException("좌표를 잘못 입력하셨습니다.");
    }
}
