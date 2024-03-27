package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.vo.Score;
import java.util.List;

public class End implements GameState {
    private final ChessBoard chessBoard;

    public End(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public GameState play(List<String> inputCommand) {
        throw new UnsupportedOperationException("종료한 게임은 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public Score calculateScore(Color color) {
        throw new UnsupportedOperationException("종료된 게임은 점수를 계산할 수 없습니다.");
    }

    @Override
    public Color getWinnerColor() {
        throw new UnsupportedOperationException("종료된 게임은 승패를 판단할 수 없습니다.");
    }

    public boolean isEndByKingCaptured() {
        return chessBoard.isKingCaptured();
    }
}
