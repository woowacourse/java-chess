package chess.domain.state;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.ChessBoard;
import chess.domain.Command;

public class End implements State {

    @Override
    public double calculateBlackScore(ChessBoard chessBoard) {
        return chessBoard.calculateByTeam(BLACK);
    }

    @Override
    public double calculateWhiteScore(ChessBoard chessBoard) {
        return chessBoard.calculateByTeam(WHITE);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State start() {
        throw new IllegalArgumentException();
    }

    @Override
    public State stop() {
        throw new IllegalArgumentException();
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        throw new IllegalArgumentException("게임을 종료합니다.");
    }
}
