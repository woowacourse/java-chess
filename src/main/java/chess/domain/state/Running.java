package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class Running extends AbstractChessState {

    private final Turn turn;

    protected Running(final ChessBoard chessBoard, final Turn turn) {
        super(chessBoard);
        this.turn = turn;
    }

    @Override
    public ChessState command(final Command command) {
        if (command.getCommand().equals("start")) {
            throw new IllegalArgumentException();
        }
        if (command.getCommand().equals("end")) {
            return new End(chessBoard);
        }
        if (!command.getCommand().equals("move")) {
            throw new IllegalArgumentException();
        }
        final List<String> parameters = command.getParameters();

        String source = parameters.get(0);
        String destination = parameters.get(1);

        PiecePosition from = PiecePosition.of(Integer.parseInt(source.split("")[1]), source.charAt(0));
        PiecePosition to = PiecePosition.of(Integer.parseInt(destination.split("")[1]), destination.charAt(0));

        chessBoard.movePiece(turn, from, to);

        return new Running(chessBoard, turn.change());
    }

    @Override
    public boolean runnable() {
        return true;
    }
}
