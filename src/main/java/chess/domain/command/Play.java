package chess.domain.command;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.ChessGame;
import chess.domain.board.GameResultBySide;
import chess.domain.board.ResultCalculator;
import chess.domain.board.ScoreBySide;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import chess.domain.service.ChessGameService;

public class Play implements CommandStatus {

    private final ResultCalculator resultCalculator;
    private final ChessGameService chessGameService;

    public Play(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
        this.resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
    }

    @Override
    public CommandStatus start() {
        chessGameService.saveNewChessGame();
        return new Play(chessGameService);
    }

    @Override
    public CommandStatus restart(Long previousGameId) {
        throw new IllegalStateException("[ERROR] 플레이 상태에서는 이전 게임으로 재시작할 수 없습니다.");
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
        saveResult();
        return new End(resultCalculator);
    }

    @Override
    public CommandStatus printGameResult() {
        saveResult();
        return new PrintGameResult(resultCalculator, chessGameService);
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
        return chessGameService.getPieces();
    }

    @Override
    public String getTurnDisplayName() {
        return chessGameService.getTurnDisplayName();
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
