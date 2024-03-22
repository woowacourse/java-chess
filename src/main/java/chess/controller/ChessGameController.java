package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionDto;
import chess.view.display.BoardDisplayConverter;
import chess.view.display.RankDisplay;
import java.util.List;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printInitMessage();
        Board board = BoardInitializer.createBoard();
        BoardDisplayConverter converter = new BoardDisplayConverter();

        Command command = inputView.readCommand();
        if (command.isStart()) {
            startGame(board, converter);
        }
    }

    private void startGame(Board board, BoardDisplayConverter converter) {
        printBoard(converter, board);
        Command command = inputView.readCommand();
        while (!command.isEnd()) {
            proceedTurn(board, converter, command);
            command = inputView.readCommand();
        }
    }

    private void proceedTurn(Board board, BoardDisplayConverter converter, Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작된 게임입니다.");
        }
        Position source = readPosition();
        Position destination = readPosition();
        board.move(source, destination);
        printBoard(converter, board);
    }

    private void printBoard(BoardDisplayConverter converter, Board board) {
        List<RankDisplay> rankDisplays = converter.convert(board.pieces());
        outputView.printBoard(rankDisplays);
    }

    private Position readPosition() {
        PositionDto positionDto = inputView.readPosition();
        File file = File.from(positionDto.fileName());
        Rank rank = Rank.from(positionDto.rankNumber());
        return Position.of(file, rank);
    }
}
