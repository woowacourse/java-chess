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

    public ChessGame(Map<Position, Piece> board) {
        this.chessState = new BlankChessState(board);
        this.turnColor = Color.WHITE;
    }

    public void move(Positions positions) {
        chessState = chessState.changeStrategy(positions.from());
        chessState.move(turnColor, positions);
        changeTurnColor();
    }

    private void changeTurnColor() {
        turnColor = turnColor.findOppositeColor();
    }

    public Scores calculateScores() {
        Score whiteScore = chessState.calculateScore(Color.WHITE);
        Score blackScore = chessState.calculateScore(Color.BLACK);
        return new Scores(whiteScore, blackScore);
    }

    public Map<Position, PieceType> collectBoard() {
        return chessState.collectBoard();
    }
}
