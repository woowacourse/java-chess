package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.pieces.piece.Color;
import chess.domain.square.Square;
import chess.dto.PieceResponse;

import java.util.List;

public class Game {

    private static final String INVALID_TURN = "헤당 색의 턴이 아닙니다.";
    private Color turn;
    private final Board board;

    public Game() {
        this.turn = Color.WHITE;
        this.board = BoardFactory.createBoard();
    }

    public void movePiece(final String source, final String destination) {
        Square from = Square.from(source);
        Square to = Square.from(destination);
        validateTurn(from);
        board.move(from, to);
        exchangeTurn();
    }

    private void validateTurn(final Square square) {
        if (!board.checkTurn(square, turn)) {
            throw new IllegalArgumentException(INVALID_TURN);
        }
    }

    private void exchangeTurn() {
        turn = turn.exchangeTurn();
    }

    public List<PieceResponse> getBoardStatus() {
        return board.createBoardStatus();
    }
}
