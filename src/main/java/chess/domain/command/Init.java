package chess.domain.command;

import java.util.List;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.GameResultBySide;
import chess.domain.board.ResultCalculator;
import chess.domain.board.ScoreBySide;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

public class Init implements CommandStatus {

    @Override
    public CommandStatus start() {
        Board board = new Board(new Pieces());
        ResultCalculator resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        return new Play(new ChessGame(board, resultCalculator), Turn.WHITE);
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 기물을 움직일 수 없습니다.");
    }

    @Override
    public CommandStatus end() {
        return new End();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public List<Piece> getPieces() {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Override
    public String getTurnDisplayName() {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 턴 이름을 반환할 수 없습니다.");
    }
}
