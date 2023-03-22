package chess.domain.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;

import java.util.Map;

public class MovePiece implements ChessGameState {

    private final Turn turn;

    public MovePiece(final Turn turn) {
        this.turn = turn;
    }

    @Override
    public boolean playable() {
        return true;
    }

    @Override
    public ChessGameState movePiece(final ChessBoard chessBoard, final PiecePosition source, final PiecePosition destination) {
        chessBoard.movePiece(turn, source, destination);

        return judgeState(chessBoard);
    }

    private ChessGameState judgeState(final ChessBoard chessBoard) {
        if (chessBoard.existKingByColor(turn.enemyColor())) {
            return new MovePiece(turn.change());
        }
        return new EndGame(turn.color());
    }

    @Override
    public ChessGameState end() {
        return new EndGame(Color.NONE);
    }

    @Override
    public Color winColor() {
        throw new IllegalArgumentException("아직 게임이 진행중입니다.");
    }

    @Override
    public Map<Color, Double> calculateScore(final ChessBoard chessBoard) {
        return chessBoard.calculateScore();
    }

    @Override
    public Turn turn() {
        return turn;
    }
}
