package chess.domain;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.state.BlankChessState;
import chess.domain.state.ChessState;
import chess.score.Score;
import chess.score.Scores;
import java.util.Map;

public class ChessGame {
    private ChessState chessState;
    private Color turnColor;

    public ChessGame(Map<Position, Piece> board, Color turnColor) {
        this.chessState = new BlankChessState(board);
        this.turnColor = turnColor;
    }

    public ChessGame(Map<Position, Piece> board) {
        this(board, Color.WHITE);
    }

    public void move(Positions positions) {
        chessState = chessState.changeStrategy(positions.from());
        chessState.move(turnColor, positions);
        changeTurnColor();
    }

    private void changeTurnColor() {
        turnColor = turnColor.findOppositeColor();
    }

    public boolean isKingCaptured() {
        return chessState.isKingCaptured();
    }

    public Color findWinner() {
        if (!isKingCaptured()) {
            throw new IllegalStateException("아직 게임이 끝나지 않았습니다.");
        }
        return chessState.announceCapturedKingColor().findOppositeColor();
    }

    public Color findLoser() {
        if (!isKingCaptured()) {
            throw new IllegalStateException("아직 게임이 끝나지 않았습니다.");
        }
        return chessState.announceCapturedKingColor();
    }

    public Scores calculateScores() {
        Score whiteScore = chessState.calculateScore(Color.WHITE);
        Score blackScore = chessState.calculateScore(Color.BLACK);
        return new Scores(whiteScore, blackScore);
    }

    public Color turn() {
        return turnColor;
    }

    public Map<Position, PieceType> collectBoard() {
        return chessState.collectBoard();
    }
}
