package chess.domain;

import chess.domain.game.command.ChessGameCommand;
import chess.domain.game.command.EndCommand;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.StartCommand;
import chess.domain.game.command.StatusCommand;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.RunGame;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.ChessBoardFactory;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.repository.mapper.FileDtoMapper;
import chess.domain.repository.mapper.PieceDtoMapper;
import chess.domain.repository.mapper.PositionValueConverter;
import chess.domain.repository.mapper.RankDtoMapper;
import chess.domain.repository.entity.BoardEntity;
import chess.domain.repository.entity.PieceEntity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessGameService {
    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessGameService(BoardDao boardDao, PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public boolean isGameExist() {
        return boardDao.existBoard();
    }

    public ChessGame loadExistGame() {
        if (!isGameExist()) {
            throw new IllegalStateException("불러올 게임이 존재하지 않습니다.");
        }

        List<PieceEntity> allPieces = pieceDao.findAllPieces();
        BoardEntity board = boardDao.findExistBoard();
        Map<Position, Piece> pieceByPosition = convertToPieceByPosition(allPieces);

        ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        return new RunGame(chessBoard, Camp.valueOf(board.getTurn()));
    }

    private Map<Position, Piece> convertToPieceByPosition(List<PieceEntity> allPieces) {
        Map<Position, Piece> pieceByPosition = new LinkedHashMap<>();

        for (PieceEntity pieceEntity : allPieces) {
            Position position = convertToPosition(pieceEntity);
            String pieceType = pieceEntity.getPieceType();
            String camp = pieceEntity.getCamp();
            Piece piece = PieceDtoMapper.convertToPiece(pieceType, Camp.valueOf(camp));
            pieceByPosition.put(position, piece);
        }

        return pieceByPosition;
    }

    private Position convertToPosition(PieceEntity pieceEntity) {
        String[] split = pieceEntity.getPosition().split("");
        File file = FileDtoMapper.convertToFile(split[0]);
        Rank rank = RankDtoMapper.convertToRank(split[1]);

        return Position.of(file, rank);
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
