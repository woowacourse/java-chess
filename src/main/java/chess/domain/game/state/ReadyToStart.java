package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

public class ReadyToStart implements GameState {

    @Override
    public GameState start() {
        return new WhiteTurn();
    }

    @Override
    public ScoreResult status() {
        throw new IllegalStateException("게임중이 아니므로 점수를 출력할 수 없습니다.");
    }

    @Override
    public GameState move(Position from, Position to) {
        throw new IllegalStateException("게임중이 아니므로 말을 이동할 수 없습니다.");
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("게임중이 아니므로 체스판이 아직 생성되지 않았습니다.");
    }

    @Override
    public PieceColor getWinColor() {
        throw new IllegalStateException("게임중이 아니므로 승자를 가져올 수 없습니다.");
    }

    @Override
    public boolean isWhiteTurn() {
        throw new IllegalStateException("게임중이 아니므로 백팀의 차례인지 가져올 수 없습니다.");
    }
}
