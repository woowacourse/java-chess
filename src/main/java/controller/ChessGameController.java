package controller;

import domain.chessboard.Row;
import domain.coordinate.Coordinate;
import domain.game.ChessGame;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.dto.Commands;
import view.dto.CoordinateRequest;
import view.util.Command;
import view.util.PieceTranslator;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameGuide();

        ChessGame chessGame = initializeBoard();
        printCurrentBoardStatus(chessGame);

        start(chessGame);
    }

    private ChessGame initializeBoard() {
        Commands commands = inputView.receiveCommands();

        if (Command.START != commands.command()) {
            throw new IllegalArgumentException("게임을 먼저 시작하세요.");
        }
        return new ChessGame();
    }

    private void start(ChessGame chessGame) {
        boolean isPlaying;

        do {
            isPlaying = playGame(chessGame);
        } while (isPlaying);
    }

    private boolean playGame(ChessGame chessGame) {
        Commands commands = inputView.receiveCommands();

        if (isCommandMove(commands)) {
            move(chessGame, commands);
            printCurrentBoardStatus(chessGame);
            return true;
        }
        return false;
    }

    private boolean isCommandMove(Commands commands) {
        if (commands.command() == Command.END) {
            return false;
        }
        if (commands.command() == Command.START) {
            throw new IllegalArgumentException("이미 시작한 상태 입니다.");
        }
        return true;
    }

    private void move(ChessGame chessGame, Commands commands) {
        Coordinate start = createCoordinate(commands.startCoordinate());
        Coordinate destination = createCoordinate(commands.destinationCoordinate());

        chessGame.startTurn(start, destination);
    }

    private Coordinate createCoordinate(CoordinateRequest coordinateRequest) {
        return new Coordinate(coordinateRequest.row(), coordinateRequest.column());
    }

    private void printCurrentBoardStatus(ChessGame chessGame) {
        List<List<String>> boardStatus = chessGame.getCurrentBoard().stream()
                .map(this::getRowStatus)
                .toList();
        outputView.printBoard(boardStatus);
    }

    private List<String> getRowStatus(Row row) {
        return row.getPieces().stream()
                .map(this::getPieceName)
                .toList();
    }

    private String getPieceName(ChessPiece chessPiece) {
        PieceTranslator pieceTranslator = PieceTranslator.from(chessPiece);
        String name = pieceTranslator.getName();

        if (chessPiece.hasSameColor(Color.WHITE)) {
            return name;
        }
        return name.toUpperCase();
    }
}
