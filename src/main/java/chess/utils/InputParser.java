package chess.utils;

import chess.domain.GameState;
import chess.domain.square.Square;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static GameState parseState(String input) {
        String[] commands = input.split(" ");
        int count = commands.length;
        if (count == 1) {
            GameState gameState = GameState.of(input);
            if (!(gameState == GameState.START || gameState == GameState.END || gameState == GameState.STATUS)) {
                throw new IllegalArgumentException("잘못된 입력입니다: 1개이면 start, end, status 중에 하나");
            }
            return GameState.of(input);
        }
        if (count == 3) {
            if (GameState.of(commands[0]) != GameState.MOVE) {
                throw new IllegalArgumentException("잘못된 입력입니다: 3개이면 move여야 합니다");
            }
            return GameState.MOVE;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다: 1개 혹은 3개여야 합니다");
    }

    public static List<Square> parseMoveSourceDestination(String input) {
        String[] moveInput = input.split(" ");
        if (GameState.of(moveInput[0]) != GameState.MOVE) {
            throw new IllegalArgumentException("move여야 합니다");
        }
        List<Square> moveSourceDestination = new ArrayList<>();
        moveSourceDestination.add(Square.of(moveInput[1]));
        moveSourceDestination.add(Square.of(moveInput[2]));
        return moveSourceDestination;
    }

}
