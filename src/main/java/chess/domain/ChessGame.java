package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.Map;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public void move(Position source, Position target) {
        state = state.move(source, target);
    }

    public void end() {
        state = state.end();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean isRightTurn(String turn) {
        return state.isRightTurn(turn);
    }

    public Score computeScore(Color color) {
        return state.computeScore(color);
    }

    public void loadTurn() {
        state.loadTurn();
    }

    public void loadBoard(Map<String, Piece> pieces) {
        state = state.loadBoard(pieces);
    }

    public Piece findPiece(String from) {
        Position position = Positions.findPosition(from);
        return state.getChessboard().findPiece(position.row(), position.column());
    }

    public Map<String, Piece> toBoardModel() {
        return state.getChessboard().toModel();
    }

    public String getTurn() {
        return state.turn();
    }

    public Chessboard getChessBoard() {
        return state.getChessboard();
    }
}
