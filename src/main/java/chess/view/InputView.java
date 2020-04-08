package chess.view;

import chess.model.domain.piece.Type;
import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    public static String inputGameState() {
        return SCANNER.nextLine();
    }

    public static Type inputChangeType() {
        Type type;
        while (true) {
            try {
                System.out.println("Q(QUEEN), B(BISHOP), N(KNIGHT), R(ROOK) 중 하나를 선택해주세요.");
                type = Type.of(SCANNER.nextLine());
                if (type.canChangeFromPawn()) {
                    break;
                }
            } catch (IllegalArgumentException ignored) {
            }
        }
        return type;
    }
}
