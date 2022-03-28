package chess.domain.board.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public abstract class Playing extends GameStarted {

    public Playing(Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Winner findWinner() {
        throw new IllegalStateException("게임이 아직 진행 중 입니다.");
    }

    @Override
    public GameState move(Position start, Position target) {
        Piece targetPiece = board.move(start, target, isBlackTurn());
        return judgeStatus(targetPiece);
    }

    private GameStarted judgeStatus(Piece piece) {
        if (piece.isKing()) {
            return judgeWinner();
        }
        return judgeTurn();
    }

    private End judgeWinner() {
        if (isBlackTurn()) {
            return new BlackWin(board);
        }
        return new WhiteWin(board);
    }

    private Playing judgeTurn() {
        if (isBlackTurn()) {
            return new WhiteTurn(board);
        }
        return new BlackTurn(board);
    }

    @Override
    public GameState terminate() {
        return new Terminate(board);
    }
}
