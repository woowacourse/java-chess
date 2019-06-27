package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessGame {
    private PieceColor turn;
    private Board board;

    public ChessGame(PieceColor turn, Map<Tile, Piece> boardState) {
        this.turn = turn;
        this.board = new Board(boardState);
    }

    public ChessGame() {
        this(PieceColor.WHITE, BoardInitializer.initialize());
    }

    public void move(String from, String to) {
        checkTurn(from);
        checkTargetColor(from, to);

        Optional<Piece> removed = board.order(from, to);
        turn = turn.opposite();

        checkKingDie(removed);
    }

    private void checkTargetColor(String from, String to) {
        if (board.isSamePieceColor(from, to)) {
            throw new InvalidMovingException("같은 색의 말이 있는 곳으로 이동 불가");
        }
    }

    private void checkTurn(String from) {
        if (board.at(from).isColor(turn.opposite())) {
            throw new InvalidMovingException(turn + " 차례 입니다.");
        }
    }

    private void checkKingDie(Optional<Piece> removedPiece) {
        if (!removedPiece.isPresent()) {
            return;
        }
        if (removedPiece.get().isKing()) {
            throw new GameOverException("game over");
        }
    }

    public Map<PieceColor, Double> status() {
        return new HashMap<PieceColor, Double>() {{
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
