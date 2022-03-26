package chess;

import chess.domain.ChessBoard;
import chess.domain.CommandType;
import chess.domain.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.NormalPiecesGenerator;
import chess.domain.piece.PiecesGenerator;
import chess.view.InputView;
import chess.view.ResultView;

public class ChessGame {

    private ChessBoard chessBoard;
    private Color turn;

    public ChessGame() {
        this.turn = Color.WHITE;
    }

    public void playGame() {
        ResultView.printStartMessage();
        GameCommand gameCommand;
        do {
            gameCommand = requestCommand();
        } while (!gameCommand.isSameCommandType(CommandType.END));
    }

    private GameCommand requestCommand() {
        try {
            GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            playGameByCommand(gameCommand);
            return gameCommand;
        } catch (RuntimeException exception) {
            ResultView.printReplay(exception.getMessage());
            return requestCommand();
        }
    }

    private void playGameByCommand(GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.START)) {
            initChessBoard();
            return;
        }
        if (gameCommand.isSameCommandType(CommandType.END)) {
            return;
        }
        validateInitBoard();
        if (gameCommand.isSameCommandType(CommandType.STATUS)) {
            System.out.println("Statsusjdlkfl");
            return;
        }
        doMovementByTurn(gameCommand);
    }

    private void initChessBoard() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        chessBoard = new ChessBoard(piecesGenerator);
        ResultView.printChessBoard(chessBoard.getPieces());
    }

    private void validateInitBoard() {
        if (chessBoard == null) {
            throw new IllegalStateException("체스판이 초기화되지 않았습니다.");
        }
    }

    private void doMovementByTurn(GameCommand gameCommand) {
        if (chessBoard.getColor(gameCommand.getFromPosition()) != turn) {
            throw new IllegalArgumentException("당신의 차례가 아닙니다.");
        }
        chessBoard.move(gameCommand);
        turn = turn.getReverseColor();
        ResultView.printChessBoard(chessBoard.getPieces());
    }
}
