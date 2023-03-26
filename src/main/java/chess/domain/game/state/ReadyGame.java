package chess.domain.game.state;

import chess.domain.repository.BoardDao;
import chess.domain.repository.PieceDao;
import chess.domain.game.result.GameResult;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.ChessBoard;
import chess.domain.position.ChessBoardFactory;
import chess.domain.position.Position;
import chess.domain.repository.entity.PieceEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReadyGame implements ChessGame {

    private static final String NOT_START_GAME = "게임이 시작되지 않았습니다. 게임을 먼저 시작해주세요.";
    private static final Camp INIT_CAMP = Camp.WHITE;

    private final PieceDao pieceDao = new PieceDao();
    private final BoardDao boardDao = new BoardDao();

    @Override
    public ChessGame startGame() {
        ChessBoard chessBoard = ChessBoardFactory.getInitialChessBoard();
        try {
            Long newBoardId = boardDao.saveBoard(INIT_CAMP.name(), "START");
            List<PieceEntity> pieceEntities = getPieceEntities(chessBoard, newBoardId);
            pieceDao.savePieces(pieceEntities);
        } catch (RuntimeException e) {
            throw new IllegalStateException();
        }

        return new RunGame(chessBoard, INIT_CAMP);
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

    @Override
    public ChessGame move(Position fromPosition, Position toPosition) {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public boolean isRunnableGame() {
        return true;
    }

    @Override
    public ChessGame endGame() {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public ChessGame status() {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public Map<Position, Piece> getPiecesPosition() {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public GameResult calculateResult() {
        throw new IllegalStateException(NOT_START_GAME);
    }
}
