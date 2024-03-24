package chess;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessSpaceGenerator;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessMachine {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessMachine(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printStartGameMessage();
        outputView.printCommandGuideMessage();

        validateFirstCommand();

        ChessBoard chessBoard = new ChessBoard(new ChessSpaceGenerator());
        outputView.printChessBoard(chessBoard.getSpaces());

        playChess(chessBoard);
    }

    private void validateFirstCommand() {
        if (inputView.getCommand() != Command.START) {
            throw new IllegalArgumentException("게임을 먼저 시작해야합니다.");
        }
    }

    private void playChess(ChessBoard chessBoard) {
        Command command = inputView.getCommand();
        while (command != Command.END) {
            validateCommandIsMove(command);
            movePiece(chessBoard);

            outputView.printChessBoard(chessBoard.getSpaces());

            command = inputView.getCommand();
        }
    }

    private void movePiece(ChessBoard chessBoard) {
        Position from = Position.of(inputView.getMoveCommand());
        Position to = Position.of(inputView.getMoveCommand());
        chessBoard.move(from, to);
    }

    private void validateCommandIsMove(Command command) {
        if (command != Command.MOVE) {
            throw new IllegalArgumentException("잘못된 명령어 입니다.");
        }
    }
}

