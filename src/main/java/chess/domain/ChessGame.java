package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.List;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public class ChessGame {

    private final Board board;
    private Team turn;

    private ChessGame(Board board, Team turn) {
        this.board = board;
        this.turn = turn;
    }

    public static ChessGame create() {
        return new ChessGame(BoardGenerator.createBoard(), WHITE);
    }

    public void movePiece(Position from, Position to) {
        board.movePiece(from, to);

    }

    public boolean isGameEnd() {
        return board.isKingDead(WHITE) || board.isKingDead(BLACK);
    }

    public List<List<Piece>> getBoard() {
        return board.getBoard();
    }

}
