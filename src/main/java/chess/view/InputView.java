package chess.view;

import chess.domain.Command;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Position;
import chess.view.mapper.CommandMapper;
import chess.view.mapper.FileMapper;
import chess.view.mapper.RankMapper;

import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final Scanner scanner = new Scanner(System.in);

    public Command readStartOrEndCommand() {
        String message = String.join(LINE_SEPARATOR,
                "> 체스 게임을 시작합니다.",
                "> 게임 시작 : start",
                "> 게임 종료 : end",
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"
        );
        System.out.println(message);

        return CommandMapper.toStartOrEndCommand(scanner.nextLine());
    }

    public Command readMoveOrEndCommand() {
        return CommandMapper.toMoveOrEndCommand(scanner.next());
    }

    public Position readSourcePosition() {
        return readPosition();
    }

    public Position readTargetPosition() {
        Position position = readPosition();
        scanner.nextLine();
        return position;
    }

    private Position readPosition() {
        String input = scanner.next();
        File file = FileMapper.from(input.substring(0, 1));
        Rank rank = RankMapper.from(input.substring(1));
        return new Position(file, rank);
    }
}
