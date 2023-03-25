package chess.domain.command;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.dao.JdbcChessGameDao;
import chess.domain.service.ChessGame;
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
        JdbcChessGameDao chessGameDao = JdbcChessGameDao.getInstance();
        Long gameId = chessGameDao.saveNewChessGame();
        return new Play(new ChessGame(gameId, board, Turn.WHITE));
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 기물을 움직일 수 없습니다.");
    }

    @Override
    public CommandStatus end() {
        return new End(new ResultCalculator(new ScoreBySide(), new GameResultBySide()));
    }

    @Override
    public CommandStatus printGameResult() {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 게임 결과를 출력할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean canPrintGameResult() {
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

    @Override
    public ScoreBySideDto getScoreBySide() {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 점수를 가져올 수 없습니다.");
    }

    @Override
    public GameResultBySideDto getGameResultBySide() {
        throw new IllegalStateException("[ERROR] 초기 상태에서는 결과를 가져올 수 없습니다.");
    }
}
