package chess.controller;

import chess.dao.BoardDao;
import chess.dao.GameRoomDao;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.RoomName;
import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.dto.BoardDto;
import chess.dto.GameRoomDto;
import chess.dto.PieceRenderer;
import chess.dto.SquareRenderer;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BoardDao boardDao;
    private final GameRoomDao gameRoomDao;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.boardDao = new BoardDao();
        this.gameRoomDao = new GameRoomDao();
    }

    public void run() {
        RoomName roomName = new RoomName(inputView.requestRoomName());

        ChessGame chessGame = new ChessGame(roomName);

        outputView.printStartMessage();
        initialize(chessGame);
        if (retryOnInvalidUserInput(this::isStartCommand)) {
            play(chessGame);
        }
        finalize(chessGame);
    }

    private void initialize(ChessGame chessGame) {
        List<BoardDto> recordedBoard = boardDao.findAllByRoomName(chessGame.getRoomName());
        gameRoomDao.findByRoomName(chessGame.getRoomName())
                .ifPresent(gameRoom -> {
                    if (!gameRoom.isWhiteTurn()) {
                        chessGame.passTurn();
                    }
                });

        Chessboard chessboard = chessGame.getChessboard();
        for (BoardDto boardDto : recordedBoard) {
            Square source = SquareRenderer.render(boardDto.getSource());
            Piece piece = PieceRenderer.render(boardDto.getPiece());

            chessboard.putPiece(source, piece);
        }
    }

    private boolean isStartCommand() {
        Command command = getMainCommand(requestCommand());

        if (command.isStartCommand()) {
            return true;
        }

        throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
    }

    private List<String> requestCommand() {
        return retryOnInvalidUserInput(inputView::requestCommand);
    }

    private Command getMainCommand(List<String> command) {
        String mainCommand = command.get(Index.MAIN_COMMAND.value);

        return Command.renderToCommand(mainCommand);
    }

    private void play(ChessGame chessGame) {
        Optional<List<String>> commands;
        do {
            outputView.printChessBoard(chessGame.getChessboard());
            commands = retryOnInvalidUserInput(this::handleCommand);

            commands.ifPresent(command -> actionForCommand(chessGame, command));
        } while (commands.isPresent() && chessGame.isBothKingAlive());
    }

    private void finalize(ChessGame chessGame) {
        outputView.printChessBoard(chessGame.getChessboard());
        outputView.printScoreMessage(chessGame);

        if (gameRoomDao.findByRoomName(chessGame.getRoomName()).isEmpty()) {
            gameRoomDao.save(new GameRoomDto(chessGame.getRoomName(), chessGame.isWhiteTurn()));
            boardDao.save(createBoardDto(chessGame));
            return;
        }

        if (chessGame.isBothKingAlive()) {
            boardDao.update(createBoardDto(chessGame));
            return;
        }

        boardDao.deleteAllByRoomName(chessGame.getRoomName());
    }

    private List<BoardDto> createBoardDto(ChessGame chessGame) {
        Chessboard board = chessGame.getChessboard();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Square square : board.getBoardMap().keySet()) {
            String source = SquareRenderer.render(square);
            String piece = PieceRenderer.render(board.getPieceAt(square));

            boardDtoList.add(new BoardDto(source, piece, chessGame.getRoomName()));
        }

        return boardDtoList;
    }

    private Optional<List<String>> handleCommand() {
        List<String> commands = requestCommand();
        Command command = getMainCommand(commands);

        if (command.isStartCommand()) {
            throw new IllegalArgumentException("이미 게임이 실행중입니다.");
        }

        if (command.isEndCommand()) {
            return Optional.empty();
        }

        return Optional.of(commands);
    }

    private void actionForCommand(ChessGame chessGame, List<String> command) {
        Command mainCommand = getMainCommand(command);

        if (mainCommand.isStatusCommand()) {
            outputView.printScoreMessage(chessGame);
            return;
        }

        movePiece(chessGame, command);
        checkPromotion(chessGame, command);
    }

    private void movePiece(ChessGame chessGame, List<String> command) {
        String sourceCommand = command.get(Index.SOURCE_SQUARE.value);
        String targetCommand = command.get(Index.TARGET_SQUARE.value);
        Square source = SquareRenderer.render(sourceCommand);
        Square target = SquareRenderer.render(targetCommand);

        retryOnInvalidAction(() -> chessGame.move(source, target));
    }

    private void checkPromotion(ChessGame chessGame, List<String> command) {
        Square movedSquare = SquareRenderer.render(command.get(Index.TARGET_SQUARE.value));

        if (chessGame.canPromotion(movedSquare)) {
            PieceType pieceType = requestPieceType();
            chessGame.promotePawn(movedSquare, pieceType);
        }
    }

    private PieceType requestPieceType() {
        return retryOnInvalidUserInput(inputView::requestPiece);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }

    private void retryOnInvalidAction(ActionFunction request) {
        try {
            request.run();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private enum Index {
        MAIN_COMMAND(0),
        SOURCE_SQUARE(1),
        TARGET_SQUARE(2),
        FILE(0),
        RANK(1);

        private final int value;

        Index(int value) {
            this.value = value;
        }
    }
}
