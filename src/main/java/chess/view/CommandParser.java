package chess.view;

import chess.controller.command.Command;
import chess.controller.command.EndCommand;
import chess.controller.command.MoveCommand;
import chess.controller.command.StartCommand;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class CommandParser {
    static final String START = "start";
    static final String END = "end";
    static final String MOVE = "move";
    private static final int START_POSITION_INDEX = 1;
    private static final int DESTINATION_POSITION_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final char RANK_TO_ROW_NUMBER_WEIGHT = '0';
    private static final char FILE_TO_COLUMN_NUMBER_WEIGHT = 'a';

    public Command parse(String input) {
        if (input.startsWith(START)) {
            return new StartCommand();
        }
        if (input.startsWith(END)) {
            return new EndCommand();
        }
        if (input.startsWith(MOVE)) {
            String[] split = input.split(" ");
            String start = split[START_POSITION_INDEX];
            String destination = split[DESTINATION_POSITION_INDEX];
            return new MoveCommand(parsePosition(start), parsePosition(destination));
        }
        throw new IllegalArgumentException("잘못된 명령어가 입력되었습니다");
    }

    private Position parsePosition(String input) {
        Rank rank = Rank.from(8 - (input.charAt(RANK_INDEX) - RANK_TO_ROW_NUMBER_WEIGHT));
        File file = File.from(input.charAt(FILE_INDEX) - FILE_TO_COLUMN_NUMBER_WEIGHT);
        return new Position(file, rank);
    }
}
