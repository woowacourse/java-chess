package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.position.PiecePosition;
import chess.domain.state.command.Command;

import java.util.List;

import static chess.domain.state.command.Command.FROM_POSITION_INDEX;
import static chess.domain.state.command.Command.TO_POSITION_INDEX;

public class Running extends AbstractChessState {

    private final Turn turn;

    protected Running(final ChessBoard chessBoard, final Turn turn) {
        super(chessBoard);
        this.turn = turn;
    }

    @Override
    public ChessState command(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 진행중입니다");
        }
        if (command.isEnd()) {
            return new End(chessBoard);
        }
        validateSize(command);
        return movePiece(command);
    }

    private void validateSize(final Command command) {
        if (command.parameters().size() != 2) {
            throw new IllegalArgumentException("제대로 입력되지 않았습니다.");
        }
    }

    private Running movePiece(final Command command) {
        final List<String> parameters = command.parameters();
        final PiecePosition from = PiecePosition.of(parameters.get(FROM_POSITION_INDEX));
        final PiecePosition to = PiecePosition.of(parameters.get(TO_POSITION_INDEX));
        chessBoard.movePiece(turn, from, to);
        return new Running(chessBoard, turn.change());
    }

    @Override
    public boolean runnable() {
        return true;
    }
}
