package chess.view;

import chess.domain.Command;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.view.mapper.CommandMapper;
import chess.view.mapper.FileMapper;
import chess.view.mapper.RankMapper;

import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final Scanner scanner = new Scanner(System.in);

    public Command readFirstCommand() {
        System.out.println(startDescriptionMessage());
        return CommandMapper.toStartOrEndCommand(scanner.nextLine());
    }

    private String startDescriptionMessage() {
        return String.join(LINE_SEPARATOR, "> 체스 게임을 시작합니다.", "> 게임 시작 : start", "> 게임 종료 : end",
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public Command readNextCommand() {
        return CommandMapper.toMoveCommand(scanner.next());
    }

    public Square readPosition() {
        if (scanner.hasNext()) {
            String position = scanner.next();
            if (position.length() != 2) {
                throw new IllegalArgumentException();
            }
            File file = FileMapper.from(position.substring(0, 1));
            Rank rank = RankMapper.from(position.substring(1, 2));

            return new Square(file, rank);
        }
        throw new IllegalArgumentException();
    }

    public void readNextLine() {
        scanner.nextLine();
    }
}
