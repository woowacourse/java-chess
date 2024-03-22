package chess.controller;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.CurrentTurn;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardMaker;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.piece.Color;
import chess.dto.MoveArgumentDto;
import chess.util.ExceptionRetryHandler;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Command command = receiveStartCommand();
        if (command.type() == CommandType.START) {
            startGame();
        }
    }

    private Command receiveStartCommand() {
        return ExceptionRetryHandler.handle(inputView::readStartCommand);
    }

    // TODO: 하위 타입 캐스팅 로직 개선
    // TODO: 게임을 진행하는 로직(ex: turn 관리)을 ChessGame으로 분리
    private void startGame() {
        ChessBoard chessBoard = makeChessBoard();

        ExceptionRetryHandler.handle(() -> executeCommandsUntilEnd(chessBoard));
    }

    private ChessBoard makeChessBoard() {
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        ChessBoard chessBoard = chessBoardMaker.make(new CurrentTurn(Color.WHITE));

        outputView.printChessBoard(chessBoard.getSquares());
        return chessBoard;
    }

    private void executeCommandsUntilEnd(ChessBoard chessBoard) {
        Command command = inputView.readCommand();

        while (command.type() != CommandType.END) {
            executeCommand(command, chessBoard);
            command = inputView.readCommand();
        }
    }

    private void executeCommand(Command command, ChessBoard chessBoard) {
        if (command.type() == CommandType.MOVE) {
            movePlayerPiece(command, chessBoard);
        }
    }

    private void movePlayerPiece(Command command, ChessBoard chessBoard) {
        MoveArgumentDto moveArgumentDto = (MoveArgumentDto) command.arguments().get(0);
        Path path = makePath(moveArgumentDto);
        chessBoard.move(path);

        outputView.printChessBoard(chessBoard.getSquares());
    }

    private static Path makePath(MoveArgumentDto moveArgumentDto) {
        return new Path(
                new Position(moveArgumentDto.startRank(), moveArgumentDto.startFile()),
                new Position(moveArgumentDto.endRank(), moveArgumentDto.endFile()));
    }
}
