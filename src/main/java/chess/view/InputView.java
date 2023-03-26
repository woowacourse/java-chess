package chess.view;

import chess.domain.Command;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public String requestRoomName() {
        System.out.println("입장하실 방을 입력해주세요.");
        return scanner.nextLine();
    }

    public List<String> requestCommand() {
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("올바르지 않은 명령어 입니다.");
        }

        List<String> commands = List.of(input.split(DELIMITER, -1));
        validateCommand(commands);

        return commands;
    }

    private void validateCommand(List<String> commands) {
        Command command = Command.renderToCommand(commands.get(CommandRule.MAIN_COMMAND_INDEX.value));

        if (command == Command.MOVE) {
            validateMoveCommand(commands);
            return;
        }

        validateNormalCommand(commands);
    }

    private void validateMoveCommand(List<String> commands) {
        validateMoveCommandSize(commands);
        validateSquareCommandSize(commands);
    }

    private void validateMoveCommandSize(List<String> commands) {
        if (commands.size() != CommandRule.MOVE_COMMAND_SIZE.value) {
            throw new IllegalArgumentException("move 명령어는 [move source target] 입니다.");
        }
    }

    private void validateSquareCommandSize(List<String> commands) {
        String source = commands.get(CommandRule.SOURCE_COMMAND_INDEX.value);
        String target = commands.get(CommandRule.TARGET_COMMAND_INDEX.value);

        if (source.length() != CommandRule.SQUARE_COMMAND_SIZE.value
                || target.length() != CommandRule.SQUARE_COMMAND_SIZE.value) {
            throw new IllegalArgumentException("잘못된 위치에 대한 입력입니다.");
        }
    }

    private void validateNormalCommand(List<String> commands) {
        if (commands.size() != CommandRule.NORMAL_COMMAND_SIZE.value) {
            throw new IllegalArgumentException("존재하지 않는 명령어 입니다.");
        }
    }

    public PieceType requestPiece() {
        System.out.println("Pawn이 Promotion 가능합니다.");
        System.out.println("[Quuen : q, Bishop : b, Knight : n, Rook : r]을 입력해주세요");
        String input = scanner.nextLine();

        return PromotionPiece.renderToPieceType(input);
    }

    private enum PromotionPiece {
        QUEEN("q", PieceType.QUEEN),
        BISHOP("b", PieceType.BISHOP),
        KNIGHT("n", PieceType.KNIGHT),
        ROOK("r", PieceType.ROOK);

        private final String command;
        private final PieceType pieceType;

        PromotionPiece(String command, PieceType pieceType) {
            this.command = command;
            this.pieceType = pieceType;
        }

        private static PieceType renderToPieceType(String input) {
            return Arrays.stream(values())
                    .filter(value -> value.command.equals(input))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."))
                    .pieceType;
        }
    }

    private enum CommandRule {
        MAIN_COMMAND_INDEX(0),
        SOURCE_COMMAND_INDEX(1),
        TARGET_COMMAND_INDEX(2),
        NORMAL_COMMAND_SIZE(1),
        SQUARE_COMMAND_SIZE(2),
        MOVE_COMMAND_SIZE(3),
        ;

        private final int value;

        CommandRule(int value) {
            this.value = value;
        }
    }
}
