package chess.controller;

import chess.command.Command;
import chess.command.CommandType;
import chess.domain.Turn;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardMaker;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.piece.Color;
import chess.dto.MoveArgumentDto;
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
        Command command = inputView.readStartCommand();
        if (command.type() == CommandType.START) {
            startGame();
        }
    }

    // TODO: 하위 타입 캐스팅 로직 개선
    // TODO: 게임을 진행하는 로직(ex: turn 관리)을 ChessGame으로 분리
    private void startGame() {
        ChessBoard chessBoard = makeChessBoard();
        Command command = inputView.readCommand();
        Turn turn = new Turn(Color.WHITE);

        while (command.type() != CommandType.END) {
            tryProcess(command, chessBoard, turn);
            command = inputView.readCommand();
        }
    }

    private ChessBoard makeChessBoard() {
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
        ChessBoard chessBoard = chessBoardMaker.make();

        outputView.printChessBoard(chessBoard.getSquares());
        return chessBoard;
    }

    private void tryProcess(Command command, ChessBoard chessBoard, Turn turn) {
        if (command.type() == CommandType.MOVE) {
            processTurn(command, chessBoard, turn);
            turn.change();
        }
    }

    private void processTurn(Command command, ChessBoard chessBoard, Turn turn) {
        MoveArgumentDto moveArgumentDto = (MoveArgumentDto) command.arguments().get(0);
        Path path = makePath(moveArgumentDto);
        chessBoard.move(path, turn.getCurrentTurn());

        outputView.printChessBoard(chessBoard.getSquares());
    }

    private static Path makePath(MoveArgumentDto moveArgumentDto) {
        return new Path(
                new Position(moveArgumentDto.startRank(), moveArgumentDto.startFile()),
                new Position(moveArgumentDto.endRank(), moveArgumentDto.endFile()));
    }
}
