package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class MainController implements Controller {

    private boolean isStart = false;
    private Color turn = Color.WHITE;

    public void run() {
        final Board board = new Board(new BoardFactory());
        final Map<Position, Piece> boardMap = board.getBoard();
        OutputView.printGameStartInfo();

        playGame(board, boardMap);
    }

    private void playGame(final Board board, final Map<Position, Piece> boardMap) {
        GameCommand command = GameCommand.from(InputView.readCommand());
        while (command != GameCommand.END) {
            validateStartCommand(command);
            if (command == GameCommand.MOVE) {
                validateMoveCommandGameIsNotStart();
                final List<Position> positions = readPositions();
                board.move(positions.get(0), positions.get(1), turn);
                turn = turn.opposite();
            }
            isStart=true;
            OutputView.printBoard(boardMap);
            command = GameCommand.from(InputView.readCommand());
        }
    }

    private void validateMoveCommandGameIsNotStart() {
        if (!isStart) {
            throw new IllegalArgumentException("아직 게임이 시작되지 않았습니다.");
        }
    }

    private void validateStartCommand(final GameCommand command) {
        if (command == GameCommand.START && isStart) {
            throw new IllegalArgumentException("이미 게임이 시작되었습니다.");
        }
    }

    private List<Position> readPositions() {
        final List<String> positions = InputView.readPositions();
        final String from = positions.get(0);
        final String to = positions.get(1);
        return List.of(renderPosition(from), renderPosition(to));
    }

    private Position renderPosition(final String position) {
        final int file = position.charAt(0) - 'a' + 1;
        final int rank = position.charAt(1) - '0';
        return new Position(file, rank);
    }
}
