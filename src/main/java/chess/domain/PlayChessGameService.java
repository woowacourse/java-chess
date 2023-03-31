package chess.domain;

import chess.domain.game.command.ChessGameCommand;
import chess.domain.game.command.EndCommand;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.StartCommand;
import chess.domain.game.command.StatusCommand;
import chess.domain.game.state.ChessGame;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.ChessBoardFactory;
import chess.domain.position.Position;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.repository.entity.PieceEntity;
import chess.domain.repository.mapper.PositionValueConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PlayChessGameService {
    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public PlayChessGameService(BoardDao boardDao, PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public ChessGame start(ChessGame chessGame) {
        cleanUpGame();

        ChessGameCommand statusCommand = new StartCommand();
        ChessGame startedGame = statusCommand.execute(chessGame);
        saveInitialBoard();
        return startedGame;
    }

    public void cleanUpGame() {
        pieceDao.deleteAll();
        boardDao.deleteAll();
    }

    private void saveInitialBoard() {
        ChessBoard initialChessBoard = ChessBoardFactory.getInitialChessBoard();
        try {
            Long newBoardId = boardDao.saveBoard(Camp.WHITE.name());
            List<PieceEntity> pieceEntities = getPieceEntities(initialChessBoard, newBoardId);
            pieceDao.savePieces(pieceEntities);
        } catch (RuntimeException e) {
            throw new IllegalStateException();
        }
    }

    private List<PieceEntity> getPieceEntities(ChessBoard chessBoard, Long boardId) {
        List<PieceEntity> pieceEntities = new ArrayList<>();
        Map<Position, Piece> piecesPosition = chessBoard.getPiecesPosition();

        for (Entry<Position, Piece> positionPieceEntry : piecesPosition.entrySet()) {
            Position position = positionPieceEntry.getKey();
            Piece piece = positionPieceEntry.getValue();
            PieceEntity pieceEntity = PieceEntity.of(position, piece, boardId);
            pieceEntities.add(pieceEntity);
        }

        return pieceEntities;
    }

    public ChessGame move(ChessGame chessGame, Position from, Position to) {
        ChessGameCommand moveCommand = new MoveCommand(from, to);
        ChessGame movedGame = moveCommand.execute(chessGame);

        String toValue = PositionValueConverter.convertToValue(to);
        if (pieceDao.isExistByPosition(toValue)) {
            pieceDao.deleteByPosition(toValue);
        }

        String fromValue = PositionValueConverter.convertToValue(from);
        pieceDao.updatePiecePositionTo(fromValue, toValue);
        boardDao.updateCamp(movedGame.getCurrentCamp().name());
        return movedGame;
    }

    public ChessGame status(ChessGame chessGame) {
        ChessGameCommand statusCommand = new StatusCommand();
        return statusCommand.execute(chessGame);
    }

    public ChessGame end(ChessGame chessGame) {
        ChessGameCommand statusCommand = new EndCommand();
        return statusCommand.execute(chessGame);
    }
}
