package chess.controller;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.view.InputView;
import java.util.List;

public class MoveSubController implements SubController {

    private final Board board;
    private Color turn = Color.WHITE;

    public MoveSubController(final Board board) {
        this.board = board;
    }

    @Override
    public void run() {
        final List<Position> positions = readPositions();
        board.move(positions.get(0), positions.get(1), turn);
        turn = turn.opposite();
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
