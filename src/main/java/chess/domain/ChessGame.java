package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessGame {
    private PieceColor turn;
    private Board board;

    ChessGame(PieceColor turn, Map<Tile, Piece> boardState) {
        this.turn = turn;
        this.board = new Board(boardState);
    }

    public ChessGame() {
        this(PieceColor.WHITE, BoardInitializer.initialize());
    }

    void move(String from, String to) {
        checkTurn(from);

        Optional<Piece> removed = board.order(from, to);
        turn = turn.opposite();

        checkKingDie(removed);
    }

    private void checkTurn(String from) {
        if(board.at(from).isColor(turn.opposite())) {
            throw new InvalidMovingException(turn + " 차례 입니다.");
        }
    }

    private void checkKingDie(Optional<Piece> removedPiece) {
        if (!removedPiece.isPresent()) {
            return;
        }
        if (removedPiece.get().isType(PieceType.KING)) {
            throw new GameOverException("game over");
        }
    }

    Map<PieceColor, Double> status() {
        return new HashMap<PieceColor, Double>(){{
            put(PieceColor.BLACK, board.status(PieceColor.BLACK));
            put(PieceColor.WHITE, board.status(PieceColor.WHITE));
        }};
    }

    public Map<Tile, Piece> getBoard() {
        return board.getBoard();
    }

    public PieceColor getTurn() {
        return turn;
    }
}
