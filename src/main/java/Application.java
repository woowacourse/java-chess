import database.ChessDao;
import database.DbChessDao;
import domain.Room;
import domain.board.Board;
import domain.board.InitialChessAlignment;
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
    private static final int MIN_ROOM_NAME = 1;
    private static final int MAX_ROOM_NAME = 20;

    private static final String NOT_STARTED = "게임을 먼저 시작해야 합니다.";
    private static final String NO_SUCH_FILE = "열은 A ~ H 를 입력해야 합니다.";
    private static final String NO_SUCH_RANK = "행은 1 ~ 8 을 입력해야 합니다.";
    private static final String INVALID_COMMAND = "move, end, status 명령어만 가능합니다.";
    private static final String INVALID_MOVE_COMMAND_SIZE = "move b2 b3 등으로 입력해야 합니다.";
    private static final String NO_SUCH_ROOM = "존재하지 않는 방 번호입니다.";
    private static final String INVALID_ROOM_NAME_LENGTH = "방 이름은 1 ~ 20 글자입니다.";

    private static final ChessDao chessDao = new DbChessDao();

    public static void main(final String[] args) {
        final Command startCommand = readStartCommand();

        if (Command.LOAD.equals(startCommand)) {
            playLoadedGame();
        }
        if (Command.START.equals(startCommand)) {
            playNewGame();
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

    private static void playLoadedGame() {
        final List<Room> rooms = chessDao.findAllRooms();
        OutputView.printRooms(rooms);

        final int roomId = InputView.readRoomNumber();
        validateRoomExist(rooms, roomId);

        final Board board = chessDao.findBoardByRoomId(roomId);
        final ChessGame chessGame = ChessGame.loadGame(board);

        chessDao.deleteBoard(roomId);
        play(chessGame, roomId);
    }

    private static void validateRoomExist(final List<Room> rooms, final int roomId) {
        if (rooms.stream().noneMatch(room -> room.getId() == roomId)) {
            throw new IllegalArgumentException(NO_SUCH_ROOM);
        }
    }

    private static void playNewGame() {
        final String roomName = InputView.readRoomName();
        validateRoomNameLength(roomName);

        final long roomId = chessDao.saveRoom(new Room(roomName));
        final ChessGame chessGame = ChessGame.initGame(new InitialChessAlignment());

        chessDao.saveBoard(chessGame.getBoard(), roomId);
        play(chessGame, roomId);
    }

    private static void validateRoomNameLength(final String roomName) {
        if (roomName.length() < MIN_ROOM_NAME || MAX_ROOM_NAME < roomName.length()) {
            throw new IllegalArgumentException(INVALID_ROOM_NAME_LENGTH);
        }
    }

    private static void play(ChessGame chessGame, long roomId) {
        OutputView.printBoard(chessGame.getBoard());

        while (true) {
            try {
                playByCommand(chessGame, roomId);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
        printWinner(chessGame, roomId);
    }

    private static void playByCommand(final ChessGame chessGame, final long roomId) {
        while (chessGame.winnerNotExist()) {
            final List<String> gameOption = InputView.readPlayGameOption();
            Command command = Command.from(gameOption.get(COMMAND_OFFSET));

            if (Command.END.equals(command)) {
                chessDao.saveBoard(chessGame.getBoard(), roomId);
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

    private static Position getPositionFrom(final List<String> gameOption, int target) {
        final String inputPosition = gameOption.get(target);

        String fileInput = String.valueOf(inputPosition.charAt(FILE_OFFSET));
        String rankInput = String.valueOf(inputPosition.charAt(RANK_OFFSET));

        return Position.of(parseFile(fileInput), parseRank(rankInput));
    }

    private static File parseFile(String input) {
        try {
            return File.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NO_SUCH_FILE);
        }
    }

    private static Rank parseRank(String input) {
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

    private static void printWinner(final ChessGame chessGame, final long roomId) {
        if (!chessGame.winnerNotExist()) {
            OutputView.printWinner(chessGame.getWinner());
            chessDao.deleteRoom(roomId);
        }
    }
}
