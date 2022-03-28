package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class ChessGame {

    private final ChessBoard chessBoard;
    private State state;

    public ChessGame() {
        state = new Ready();
        chessBoard = new ChessBoard();
    }

    private void start() {
        state = state.start();
    }

    private void stop() {
        state = state.stop();
    }

    public void progress(Command command) {
        if (command.isStart()) {
            start();
            return;
        }

        if (command.isEnd()) {
            stop();
            return;
        }

        state = state.changeTurn(command, chessBoard);
    }

    public Set<Position> getPiecePositions() {
        Map<Position, Piece> cells = chessBoard.getCells();

        return Collections.unmodifiableSet(cells.keySet());
    }

    public Map<Team, Double> calculateResult() {
        return state.status(chessBoard);
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public String getSymbolByPosition(Position position) {
        Piece piece = chessBoard.getPieceByPosition(position);

        return piece.getSymbol();
    }
}
