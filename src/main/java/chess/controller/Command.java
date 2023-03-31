package chess.controller;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessgame.ChessGame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int MOVE_CURRENT_POSITION_INDEX = 1;
    private static final int MOVE_TARGET_POSITION_INDEX = 2;
    private static final int VALID_COMMON_SIZE = 3;
    private static final int COMMAND_INDEX = 0;
    private static final String DELIMITER = "";

    private final List<String> commandInputs;
    private final GameState gameState;

    public Command(final List<String> commandInputs) {
        this.commandInputs = commandInputs;
        this.gameState = GameState.valueOfCommand(commandInputs.get(COMMAND_INDEX));
    }

    public void validateCommandSize() {
        if (commandInputs.size() != VALID_COMMON_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 이동 입력입니다.");
        }
    }

    public Position getCurrentPosition() {
        final String fileRankInput = commandInputs.get(MOVE_CURRENT_POSITION_INDEX);
        return generatePositionBy(fileRankInput);
    }

    private Position generatePositionBy(String fileRankInput) {
        final List<String> positionLetters = generatePositionLetters(fileRankInput);
        validatePositionLetterSize(positionLetters);
        final String fileValue = String.valueOf(positionLetters.get(FILE_INDEX));
        final String rankValue = String.valueOf(positionLetters.get(RANK_INDEX));
        return new Position(File.valueOfName(fileValue), Rank.valueOfName(rankValue));
    }

    private List<String> generatePositionLetters(final String fileRankInput) {
        return Arrays.stream(fileRankInput.split(DELIMITER)).collect(Collectors.toList());
    }

    private void validatePositionLetterSize(final List<String> positionLetters) {
        if (positionLetters.size() != 2) {
            throw new IllegalArgumentException("유효하지 않은 위치 입력입니다.");
        }
    }

    public Position getTargetPosition() {
        final String fileRankInput = commandInputs.get(MOVE_TARGET_POSITION_INDEX);
        return generatePositionBy(fileRankInput);
    }

    public ChessGame execute(final ChessGame chessGame) {
        return gameState.execute(this, chessGame);
    }
}
