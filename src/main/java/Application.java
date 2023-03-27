import database.ChessDao;
import database.DbChessDao;
import domain.Room;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public final class Application {
    private static final int COMMAND_OFFSET = 0;
    private static final int SOURCE_OFFSET = 1;
    private static final int DESTINATION_OFFSET = 2;
    private static final int FILE_OFFSET = 0;
    private static final int RANK_OFFSET = 1;
    private static final int MOVE_COMMAND_SIZE = 3;

    private static final String NOT_STARTED = "게임을 먼저 시작해야 합니다.";
    private static final String NO_SUCH_FILE = "열은 A ~ H 를 입력해야 합니다.";
    private static final String NO_SUCH_RANK = "행은 1 ~ 8 을 입력해야 합니다.";
    private static final String INVALID_COMMAND = "move, end, status 명령어만 가능합니다.";
    private static final String INVALID_MOVE_COMMAND_SIZE = "move b2 b3 등으로 입력해야 합니다.";

    public static void main(final String[] args) {
        final DbChessDao dao = new DbChessDao();
        final Command startCommand = readStartCommand();

        if (Command.LOAD.equals(startCommand)) {
            playLoadGame(dao);
        }
        if (Command.START.equals(startCommand)) {
            playNewGame(dao);
        }
    }

    private static Command readStartCommand() {
        try {
            final Command command = Command.from(InputView.readStartOption());
            validateStartCommand(command);

            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return readStartCommand();
        }
    }

    private static void validateStartCommand(final Command command) {
        if (Command.START != command
                && Command.END != command
                && Command.LOAD != command) {
            throw new IllegalArgumentException(NOT_STARTED);
        }
    }

    private static void playLoadGame(final ChessDao dao) {
        final List<Room> rooms = ChessGame.findAllRooms(dao);
        OutputView.printRooms(rooms);

        final int roomId = InputView.readRoomNumber();
        final ChessGame chessGame = ChessGame.loadGame(dao, roomId);

        play(chessGame);
    }

    private static void playNewGame(final ChessDao dao) {
        final String roomName = InputView.readRoomName();
        final ChessGame chessGame = ChessGame.initGame(dao, new Room(roomName));
        play(chessGame);
    }

    private static void play(final ChessGame chessGame) {
        OutputView.printBoard(chessGame.getBoard());

        while (true) {
            try {
                playByCommand(chessGame);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
        printWinner(chessGame);
    }

    private static void playByCommand(final ChessGame chessGame) {
        while (chessGame.winnerNotExist()) {
            final List<String> gameOption = InputView.readPlayGameOption();
            Command command = Command.from(gameOption.get(COMMAND_OFFSET));

            if (Command.END.equals(command)) {
                chessGame.save();
                break;
            }
            if (Command.START.equals(command)) {
                throw new IllegalArgumentException(INVALID_COMMAND);
            }
            if (Command.STATUS.equals(command)) {
                OutputView.printStatus(chessGame.getBlackScore(), chessGame.getWhiteScore());
            }
            if (Command.MOVE.equals(command)) {
                move(chessGame, gameOption);
            }
        }
    }

    private static void move(final ChessGame chessGame, final List<String> gameOption) {
        if (gameOption.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException(INVALID_MOVE_COMMAND_SIZE);
        }

        final Position source = getPositionFrom(gameOption, SOURCE_OFFSET);
        final Position destination = getPositionFrom(gameOption, DESTINATION_OFFSET);
        chessGame.move(source, destination);
        OutputView.printBoard(chessGame.getBoard());
    }

    private static Position getPositionFrom(final List<String> gameOption, final int target) {
        final String inputPosition = gameOption.get(target);

        String fileInput = String.valueOf(inputPosition.charAt(FILE_OFFSET));
        String rankInput = String.valueOf(inputPosition.charAt(RANK_OFFSET));

        return Position.of(parseFile(fileInput), parseRank(rankInput));
    }

    private static File parseFile(final String input) {
        try {
            return File.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NO_SUCH_FILE);
        }
    }

    private static Rank parseRank(final String input) {
        try {
            final int inputRank = Integer.parseInt(input);
            return Arrays.stream(Rank.values())
                    .filter(rank -> !Rank.NOTHING.equals(rank))
                    .filter(rank -> inputRank == rank.getValue())
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_RANK));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NO_SUCH_RANK);
        }
    }

    private static void printWinner(final ChessGame chessGame) {
        if (!chessGame.winnerNotExist()) {
            OutputView.printWinner(chessGame.getWinner());
            chessGame.delete();
        }
    }
}
