package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.PiecePositions;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.TerminalPosition;

import java.util.Map;

public class ChessGame {
    private final Turn turn;
    private final ChessBoard board;

    public ChessGame(Turn turn, ChessBoard board) {
        this.turn = turn;
        this.board = board;
    }

    public static ChessGame createOnStart() {
        return new ChessGame(Turn.createOnStart(), PiecePositions.createBoard());
    }

    public void movePiece(TerminalPosition terminalPosition) {
        board.move(terminalPosition, turn.getCurrentTurn());
        turn.process();
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "turn=" + turn +
                ", board=" + board +
                '}';
    }
}
