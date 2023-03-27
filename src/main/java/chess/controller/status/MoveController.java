package chess.controller.status;

import chess.controller.Command;
import chess.controller.mapper.ChessBoardDtoMapper;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;
import chess.domain.piece.move.PositionConverter;
import chess.entity.PieceEntity;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public final class MoveController implements Controller {

    private final Long userId;
    private final ChessGame chessGame;
    private final ChessGameService chessGameService;

    MoveController(final Long userId, final ChessGameService chessGameService) {
        this.userId = userId;
        this.chessGame = chessGameService.getChessGame(userId);
        this.chessGameService = chessGameService;
    }

    @Override
    public Controller checkCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new EndController();
        }
        if (command.isStatus()) {
            return new StatusController(userId, chessGameService).getStatus(true);
        }
        return move(command);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public void printBoard() {
        OutputView.printBoard(ChessBoardDtoMapper.createChessBoardDto(chessGame.getChessBoard()));
    }

    Controller move(final Command command) {
        validateCommand(command);
        final List<String> commands = command.getCommands();

        play(commands);
        if (!chessGame.isKingAlive()) {
            return new StatusController(userId, chessGameService).getStatus(false);
        }
        return new MoveController(userId, chessGameService);
    }

    private void validateCommand(final Command command) {
        if (!command.isCorrectWhenMove()) {
            throw new IllegalArgumentException("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
        }
    }

    private void play(final List<String> commands) {
        final Position source = PositionConverter.convert(commands.get(1));
        final Position target = PositionConverter.convert(commands.get(2));
        chessGame.play(source, target);
        savePlayInfo(source, target);
    }

    private void savePlayInfo(final Position source, final Position target) {
        final Long chessGameId = chessGameService.getChessGameId(userId);
        deletePieces(source, target, chessGameId);
        savePieces(target, chessGameId);
        chessGameService.updateCurrentCamp(chessGameId, chessGame.getCurrentCamp());
    }

    private void deletePieces(final Position source, final Position target, final Long chessGameId) {
        final PieceEntity sourcePiece = PieceEntity.createWithLocation(chessGameId, source.getRank(), source.getFile());
        final PieceEntity targetPiece = PieceEntity.createWithLocation(chessGameId, target.getRank(), target.getFile());
        chessGameService.deletePieces(sourcePiece, targetPiece);
    }

    private void savePieces(final Position target, final Long chessGameId) {
        final Map<Position, Piece> chessBoard = chessGame.getChessBoard();
        final Piece piece = chessBoard.get(target);
        final PieceEntity savedPiece = PieceEntity.createWithChessGameId(chessGameId, target.getRank(),
                target.getFile(), piece.getPieceType().name(), piece.getCampType().name());
        chessGameService.savePiece(savedPiece);
    }
}
