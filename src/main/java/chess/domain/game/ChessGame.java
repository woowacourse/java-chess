package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardMaker;
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
        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();

        return new ChessGame(Turn.createOnStart(), chessBoardMaker.make());
    }

    public void movePiece(TerminalPosition terminalPosition) {
        board.move(terminalPosition, turn.getCurrentTurn());
        turn.process();
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }
}
