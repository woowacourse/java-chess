package chess.domain.command;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.ChessGame;
import chess.domain.board.ResultCalculator;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import chess.domain.service.ChessGameService;

public class PrintGameResult implements CommandStatus {

    private final ResultCalculator resultCalculator;
    private final ChessGameService chessGameService;

    public PrintGameResult(final ResultCalculator resultCalculator, final ChessGameService chessGameService) {
        this.resultCalculator = resultCalculator;
        this.chessGameService = chessGameService;

    }

    @Override
    public CommandStatus start() {
        chessGameService.saveNewChessGame();
        return new Play(chessGameService);
    }

    @Override
    public CommandStatus restart(Long previousGameId) {
        throw new IllegalStateException("[ERROR] 게임 결과 출력 상태에서는 이전 게임으로 재시작할 수 없습니다.");
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        if (chessGameService.isTargetPieceOppositeKing(sourcePosition, targetPosition)) {
            chessGameService.movePiece(sourcePosition, targetPosition);
            saveResult();
            return new End(resultCalculator);
        }
        ChessGame movedChessGame = chessGameService.movePiece(sourcePosition, targetPosition);
        return new Play(new ChessGameService(movedChessGame));
    }

    private void saveResult() {
        resultCalculator.saveTotalScoreBySide(Side.WHITE, chessGameService.getTotalScoreBySide(Side.WHITE));
        resultCalculator.saveTotalScoreBySide(Side.BLACK, chessGameService.getTotalScoreBySide(Side.BLACK));
        resultCalculator.saveGameResultBySide();
    }

    @Override
    public CommandStatus end() {
        return new End(resultCalculator);
    }

    @Override
    public CommandStatus printGameResult() {
        throw new IllegalStateException("[ERROR] 게임 결과 출력 상태에서는 다시 게임 결과를 출력할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean canPrintGameResult() {
        return true;
    }

    @Override
    public List<Piece> getPieces() {
        throw new IllegalStateException("[ERROR] 게임 결과 출력 상태에서는 기물들을 반환할 수 없습니다.");
    }

    @Override
    public String getTurnDisplayName() {
        throw new IllegalStateException("[ERROR] 게임 결과 출력 상태에서는 턴 이름을 반환할 수 없습니다.");
    }

    @Override
    public ScoreBySideDto getScoreBySide() {
        return new ScoreBySideDto(resultCalculator.getScoreBySide());
    }

    @Override
    public GameResultBySideDto getGameResultBySide() {
        return new GameResultBySideDto(resultCalculator.getGameResultBySide());
    }
}
