package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.PieceColor;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGame {

    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final String FILE_RANK_DELIMITER = "";
    private static final int SOURCE_SQUARE_INDEX = 1;
    private static final int TARGET_SQUARE_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public void start() {
        OutputView.printStartMessage();
        String commandInput = InputView.readCommand();
        Command command = Command.from(commandInput);

        if (command.isEnd()) {
            return;
        }
        if (command.isMove()) {
            throw new IllegalStateException("게임을 먼저 시작해 주세요.");
        }

        Board board = BoardFactory.createBoard();
        OutputView.printBoard(board.getPieces());
        playChess(board);
    }

    private void playChess(final Board board) {
        PieceColor turn = PieceColor.WHITE;
        String commandInput = InputView.readCommand();
        Command command = Command.from(commandInput);
        if (command.isStart()) {
            throw new IllegalStateException("이미 게임이 시작되었습니다.");
        }

        while (command.isNotEnd()) {
            turn = playTurn(board, commandInput, turn);
            commandInput = InputView.readCommand();
            command = Command.from(commandInput);
        }
    }

    private PieceColor playTurn(final Board board, final String commandInput, final PieceColor turn) {
        List<String> splitCommand = List.of(commandInput.split(MOVE_COMMAND_DELIMITER));
        Square source = createSquare(splitCommand.get(SOURCE_SQUARE_INDEX));
        Square target = createSquare(splitCommand.get(TARGET_SQUARE_INDEX));

        board.move(source, target, turn);
        OutputView.printBoard(board.getPieces());
        return turn.next();
    }

    private Square createSquare(final String commandInput) {
        List<String> commandToken = List.of(commandInput.split(FILE_RANK_DELIMITER));
        File file = File.findByValue(commandToken.get(FILE_INDEX));
        Rank rank = Rank.findByValue(commandToken.get(RANK_INDEX));
        return new Square(file, rank);
    }
}
