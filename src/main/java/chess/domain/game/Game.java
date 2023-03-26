package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.exception.TeamNotMatchException;
import java.util.List;

public class Game {
    private final Board board;
    private Team turn;

    public Game() {
        board = new Board();
        turn = Team.WHITE;
    }

    public void move(Square source, Square target) {
        if (isNotCurrentTurn(source) || board.isEmptyPiece(source)) {
            throw new TeamNotMatchException(turn);
        }
        board.move(source, target);
        turn = turn.nextTurn(turn);
    }

    private boolean isNotCurrentTurn(Square square) {
        return board.isSquarePieceNotCurrentTurn(square, turn);
    }

    public boolean isGameEnd() {
        return board.haveOneKing();
    }

    public double calculateWhiteScore() {
        List<Piece> whitePieces = board.getWhitePieces();
        return calculateScore(whitePieces);
    }

    public double calculateBlackScore() {
        List<Piece> blackPieces = board.getBlackPieces();
        return calculateScore(blackPieces);
    }

    private double calculateScore(List<Piece> pieces) {
        double score = 0;
        for (Piece piece : pieces) {
            PieceType pieceType = piece.getPieceType();
            score += pieceType.getScore();
        }
        return score;
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }
}
