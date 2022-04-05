package chess.domain;

import chess.domain.move.MoveChecker;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.StatusDto;
import java.util.Map;

public class ChessGame {

    private Color turn;
    private final Board board;

    public ChessGame() {
        this.turn = Color.WHITE;
        this.board = Board.create();
    }

    public boolean move(Position from, Position to) {
        final MoveChecker moveChecker = new MoveChecker(board, turn);
        moveChecker.checkMovable(from, to);

        if (moveChecker.isCheckmate(to)) {
            return checkmate();
        }
        return movePiece(from, to);
    }

    private boolean checkmate() {
        return false;
    }

    private boolean movePiece(final Position from, final Position to) {
        board.move(from, to);
        turn = turn.getOpposite();
        return true;
    }

    public String getTurn() {
        return turn.getName();
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public StatusDto getStatus() {
        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);
        Result whiteResult = Result.decide(whiteScore, blackScore);
        Result blackResult = Result.decide(blackScore, whiteScore);
        return new StatusDto(whiteScore.getValue(), blackScore.getValue(), whiteResult.getName(), blackResult.getName());
    }
}
