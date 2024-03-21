package chess.domain;

import chess.domain.piece.Piece;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private Team turn;

    public ChessGame() {
        this.board = new Board();
    }

    public Map<Position, Piece> start() {
        board.placeInitialPieces();
        turn = Team.WHITE;
        return board.getBoard();
    }

    public Map<Position, Piece> move(Position source, Position target) {
        if (checkTurn(source, turn)) {
            board.movePieceAndRenewBoard(source, target);
            turn = turnChange();
        }
        return board.getBoard();
    }

    private boolean checkTurn(Position source, Team turn) {
        return board.getBoard().get(source).isSameTeam(turn);
    }

    private Team turnChange() {
        if (turn == Team.WHITE) {
            return Team.BLACK;
        }
        return Team.WHITE;
    }
}
