package chess;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.OriginalChessSpaceGenerator;
import chess.domain.chessBoard.PieceGenerator;
import chess.domain.piece.Color;
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

        ChessBoard chessBoard = new ChessBoard(new OriginalChessSpaceGenerator(new PieceGenerator()));
        outputView.printChessBoard(chessBoard.getSpaces());

        Color initialTurnColor = Color.WHITE;
        playChess(chessBoard, initialTurnColor);
    }

    private void validateFirstCommand() {
        if (inputView.getCommand() != Command.START) {
            throw new IllegalArgumentException("게임을 먼저 시작해야합니다.");
        }
    }

    private void playChess(ChessBoard chessBoard, Color turnColor) {
        Command command = inputView.getCommand();
        while (command != Command.END) {
            validateCommandIsMove(command);
            turnColor = consumeTurn(chessBoard, turnColor);

            outputView.printChessBoard(chessBoard.getSpaces());

            command = inputView.getCommand();
        }
    }

    private Color consumeTurn(ChessBoard chessBoard, Color turnColor) {
        Position from = inputView.getMovePosition();
        Position to = inputView.getMovePosition();

        if(isRightTurn(chessBoard, turnColor, from)){
            chessBoard.move(from, to);
            return nextTurnColor(turnColor);
        }
        return turnColor;
    }

    private boolean isRightTurn(ChessBoard chessBoard, Color turnColor, Position from) {
        if(chessBoard.isSameColor(from, turnColor)){
            return true;
        }
        outputView.printWrongTurn();
        return false;
    }

    private Color nextTurnColor(Color turnColor) {
        if(turnColor==Color.WHITE){
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    private void validateCommandIsMove(Command command) {
        if (command != Command.MOVE) {
            throw new IllegalArgumentException("잘못된 명령어 입니다.");
        }
    }
}

