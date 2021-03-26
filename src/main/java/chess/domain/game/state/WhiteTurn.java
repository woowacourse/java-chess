package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class WhiteTurn extends Running {

    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new IllegalStateException("이미 체스게임이 진행 중 입니다.");
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        Piece sourcePiece = board().pieceByPosition(source);

        if (sourcePiece.isBlack()) {
            throw new IllegalStateException("백색 차례엔 백색 말만 이동 가능합니다.");
        }

        board().moveIfValidPosition(source, target);
    }

    @Override
    public State passTurn() {
        return new BlackTurn(board());
    }

    @Override
    public List<Rank> ranks() {
        return board().ranks();
    }

    @Override
    public String finishReason() {
        throw new IllegalStateException("아직 체스게임이 진행중 입니다.");
    }

    @Override
    public Color winner() {
        throw new IllegalStateException("아직 체스게임이 진행중 입니다.");
    }

    @Override
    public State end() {
        return new End(board());
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
