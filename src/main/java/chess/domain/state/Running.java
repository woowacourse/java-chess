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
    public ChessState execute(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 진행중입니다");
        }
        if (command.isEnd()) {
            return new End(chessBoard);
        }
        return movePiece(command);
    }

    private Running movePiece(final Command command) {
        final List<PiecePosition> piecePositions = command.moveParameters();
        final PiecePosition from = piecePositions.get(FROM_POSITION_INDEX);
        final PiecePosition to = piecePositions.get(TO_POSITION_INDEX);
        chessBoard.movePiece(turn, from, to);
        return new Running(chessBoard, turn.change());
    }

    @Override
    public boolean executable() {
        return true;
    }
}
