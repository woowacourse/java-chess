package chess.domain.command;

import java.util.List;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.dao.ChessGameDao;
import chess.domain.board.Board;
import chess.domain.board.GameResultBySide;
import chess.domain.board.ResultCalculator;
import chess.domain.board.ScoreBySide;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Side;
import chess.domain.piece.dto.FindPiecePositionDto;
import chess.domain.piece.dto.SavePieceDto;
import chess.domain.piece.dto.UpdatePiecePositionDto;
import chess.domain.position.Position;
import chess.domain.service.ChessGame;

public class Play implements CommandStatus {

    private final ChessGame chessGame;
    private final ResultCalculator resultCalculator;
    private final ChessGameDao chessGameDao;

    public Play(final ChessGame chessGame, ChessGameDao chessGameDao) {
        this.chessGame = chessGame;
        this.resultCalculator = new ResultCalculator(new ScoreBySide(), new GameResultBySide());
        this.chessGameDao = chessGameDao;
    }

    @Override
    public CommandStatus start() {
        Board board = new Board(new Pieces());
        Long gameId = chessGameDao.saveNewChessGame();
        for (Piece piece : board.getPieces()) {
            chessGameDao.savePiece(new SavePieceDto(piece, gameId));
        }
        return new Play(new ChessGame(gameId, board, Turn.WHITE), chessGameDao);
    }

    @Override
    public CommandStatus restart(Long previousGameId) {
        throw new IllegalStateException("[ERROR] 플레이 상태에서는 이전 게임으로 재시작할 수 없습니다.");
    }

    @Override
    public CommandStatus move(Position sourcePosition, Position targetPosition) {
        checkTurn(sourcePosition);
        chessGame.checkPieceMoveCondition(sourcePosition, targetPosition);
        if (chessGame.isTargetPieceOppositeKing(sourcePosition, targetPosition)) {
            return gameEnd(sourcePosition, targetPosition);
        }
        piecePositionUpdateWhenOnlyMove(sourcePosition, targetPosition);
        piecePositionUpdateWhenTakePiece(sourcePosition, targetPosition);
        chessGame.movePiece(sourcePosition, targetPosition);
        Board currentBoard = new Board(new Pieces(chessGame.getPieces()));
        return new Play(new ChessGame(chessGame.getId(), currentBoard, chessGame.turnChange()), chessGameDao);
    }

    private void checkTurn(Position sorucePosition) {
        Piece sourcePiece = chessGame.findPieceByPosition(sorucePosition);
        if (!chessGame.isCorrectTurn(sourcePiece.getSide())) {
            throw new IllegalArgumentException("[ERROR] 현재 턴인 진영의 기물만 이동할 수 있습니다.");
        }
    }

    private End gameEnd(Position sourcePosition, Position targetPosition) {
        chessGame.movePiece(sourcePosition, targetPosition);
        resultCalculator.saveTotalScoreBySide(Side.WHITE, chessGame.getTotalScoreBySide(Side.WHITE));
        resultCalculator.saveTotalScoreBySide(Side.BLACK, chessGame.getTotalScoreBySide(Side.BLACK));
        resultCalculator.saveGameResultBySide();
        return new End(resultCalculator);
    }

    private void piecePositionUpdateWhenOnlyMove(Position sourcePosition, Position targetPosition) {
        if (chessGame.isOnlyMove(targetPosition)) {
            Piece sourcePiece = chessGame.findPieceByPosition(sourcePosition);
            UpdatePiecePositionDto updateTargetPiecePositionDto = new UpdatePiecePositionDto(targetPosition);
            FindPiecePositionDto findSourcePiecePositionDto = new FindPiecePositionDto(chessGame.getId(), sourcePiece.getRank(), sourcePiece.getFile());
            chessGameDao.updatePiecePosition(updateTargetPiecePositionDto, findSourcePiecePositionDto);
        }
    }

    private void piecePositionUpdateWhenTakePiece(Position sourcePosition, Position targetPosition) {
        if (chessGame.isTakePieceMove(targetPosition)) {
            Piece sourcePiece = chessGame.findPieceByPosition(sourcePosition);
            Piece targetPiece = chessGame.findPieceByPosition(targetPosition);
            UpdatePiecePositionDto updateSourcePiecePositionDto = new UpdatePiecePositionDto(sourcePosition);
            FindPiecePositionDto findSourcePiecePositionDto = new FindPiecePositionDto(chessGame.getId(), sourcePiece.getRank(), sourcePiece.getFile());
            FindPiecePositionDto findTargetPiecePositionDto = new FindPiecePositionDto(chessGame.getId(), targetPiece.getRank(), targetPiece.getFile());
            chessGameDao.deletePieceByPosition(findTargetPiecePositionDto);
            chessGameDao.updatePiecePosition(updateSourcePiecePositionDto, findSourcePiecePositionDto);
        }
    }

    @Override
    public CommandStatus end() {
        resultCalculator.saveTotalScoreBySide(Side.WHITE, chessGame.getTotalScoreBySide(Side.WHITE));
        resultCalculator.saveTotalScoreBySide(Side.BLACK, chessGame.getTotalScoreBySide(Side.BLACK));
        resultCalculator.saveGameResultBySide();
        return new End(resultCalculator);
    }

    @Override
    public CommandStatus printGameResult() {
        resultCalculator.saveTotalScoreBySide(Side.WHITE, chessGame.getTotalScoreBySide(Side.WHITE));
        resultCalculator.saveTotalScoreBySide(Side.BLACK, chessGame.getTotalScoreBySide(Side.BLACK));
        resultCalculator.saveGameResultBySide();
        return new PrintGameResult(chessGame, chessGameDao);
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
        return chessGame.getPieces();
    }

    @Override
    public String getTurnDisplayName() {
        return chessGame.getTurnDisplayName();
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
