package chessgame.controller;

import chessgame.domain.chessgame.BoardInitialImage;
import chessgame.domain.chessgame.Camp;
import chessgame.domain.chessgame.ChessGame;
import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.dto.GameRoomDto;
import chessgame.service.ChessGameService;
import chessgame.view.InputView;
import chessgame.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {

    public static final char ASCII_ALPHABET_A = 'a';
    public static final int START_COORDINATE_INDEX = 1;
    public static final int COLUMN_INDEX = 0;
    public static final int ROW_INDEX = 1;
    public static final int END_COORDINATE_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;

    public ChessController(final InputView inputView, final OutputView outputView, ChessGameService chessGameService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = chessGameService;
    }

    public void run() {
        Command command;

        do {
            command = readCommend();
        } while (command.canContinue());
    }

    private Command readCommend() {
        try {
            printGameStartMessage();
            List<String> commands = inputView.readCommand();
            Command command = Command.of(commands);
            processStartGame(command, commands);
            return command;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return readCommend();
        }
    }

    private void printGameStartMessage() {
        List<GameRoomDto> gameRoomDtos = chessGameService.findAllGameRoom();
        outputView.printGameStartMessage(gameRoomDtos);
    }

    private void processStartGame(final Command command, final List<String> commands) {
        if (command.isStart()) {
            ChessGame chessGame = makeGameRoom(commands);
            playGame(chessGame);
        }
        if (command.isMove() || command.isStatus()) {
            throw new IllegalArgumentException("[ERROR] 아직 게임을 시작하지 않았습니다.");
        }
    }

    private ChessGame makeGameRoom(List<String> commands) {
        String roomId = commands.get(1);
        if (roomId.equals("new")) {
            return makeNewGameRoom();
        }
        return makeAlreadyExistGameRoom(roomId);
    }

    private ChessGame makeNewGameRoom() {
        chessGameService.addNewGame(BoardInitialImage.generate(), Camp.WHITE);
        GameRoomDto gameRoomDto = chessGameService.findLeastPieces();

        long roomId = gameRoomDto.getRoomId();
        Map<Coordinate, Piece> board = chessGameService.findPiecesByRoomId(roomId);
        return new ChessGame(board, gameRoomDto);
    }

    private ChessGame makeAlreadyExistGameRoom(String roomId) {
        GameRoomDto gameRoomDto = chessGameService.findGameRoomById(Long.parseLong(roomId));
        Map<Coordinate, Piece> board = chessGameService.findPiecesByRoomId(gameRoomDto.getRoomId());
        return new ChessGame(board, gameRoomDto);
    }

    private void playGame(final ChessGame chessGame) {
        Command command;

        do {
            command = readPlayCommand(chessGame);
            printGameResultWhenEnd(command, chessGame);
        } while (command.canContinue());
    }

    private Command readPlayCommand(final ChessGame chessGame) {
        try {
            outputView.printBoard(chessGame.getBoard());
            List<String> commands = inputView.readCommand();
            Command command = Command.of(commands);
            return processPlayGame(command, commands, chessGame);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return readPlayCommand(chessGame);
        }
    }

    private Command processPlayGame(final Command command,
                                    final List<String> commands,
                                    final ChessGame chessGame) {
        if (command.isStart()) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
        }
        if (command.isMove()) {
            return processMove(commands, chessGame);
        }
        if (command.isStatus()) {
            printGameStatus(chessGame);
        }
        return command;
    }

    private Command processMove(final List<String> commands, final ChessGame chessGame) {
        Coordinate startCoordinate = convertCoordinate(commands.get(START_COORDINATE_INDEX));
        Coordinate endCoordinate = convertCoordinate(commands.get(END_COORDINATE_INDEX));
        boolean isKing = chessGame.move(startCoordinate, endCoordinate);
        if (isKing) {
            chessGameService.deleteGame(chessGame);
            return Command.END;
        }
        chessGameService.updateGame(chessGame);
        return Command.MOVE;
    }

    private Coordinate convertCoordinate(final String frontCoordinate) {
        int row = Character.getNumericValue(frontCoordinate.charAt(ROW_INDEX)) - 1;
        int column = (int) frontCoordinate.charAt(COLUMN_INDEX) - ASCII_ALPHABET_A;
        return Coordinate.fromOnBoard(row, column);
    }

    private void printGameStatus(ChessGame chessGame) {
        outputView.printGameStatus(chessGame.getStatus());
    }

    private void printGameResultWhenEnd(Command command, ChessGame chessGame) {
        if (command.isEnd()) {
            outputView.printGameResult(chessGame.getBoard(), chessGame.getStatus());
        }
    }
}
