package web.service;

import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.domain.piece.*;
import chess.dto.*;
import web.dao.BoardDao;
import web.dao.GameDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private GameState gameState;
    private final BoardDao boardDao;
    private final GameDao gameDao;

    public ChessService(BoardDao boardDao, GameDao gameDao) {
        this.gameState = new Ready();
        this.boardDao = boardDao;
        this.gameDao = gameDao;
    }

    public WebBoardDto startNewGame(GameDto gameDto) {
        gameDao.save(gameDto);
        gameState = this.gameState.start(new Board(InitialBoardGenerator.generate()), Color.WHITE);

        BoardAndTurnInfo response = (BoardAndTurnInfo) gameState.getResponse();
        Map<Point, Piece> responseBoard = response.getBoard();
        for (Point point : responseBoard.keySet()) {
            savePiece(gameDto, responseBoard, point);
        }
        return new WebBoardDto(response, true);
    }

    private void savePiece(GameDto gameDto, Map<Point, Piece> responseBoard, Point point) {
        if (!responseBoard.get(point).isSameType(PieceType.EMPTY)) {
            boardDao.save(new PieceDto(gameDto.getRoomName(), point.convertPointToId(),
                    responseBoard.get(point).getPieceType(), responseBoard.get(point).getPieceColor()));
        }
    }

    public WebBoardDto resumeGame(GameDto gameDto) {
        GameDto findGame = gameDao.findByRoomName(gameDto.getRoomName());
        if (findGame == null) {
            throw new IllegalArgumentException("[ERROR] 존재하는 게임이 없습니다.");
        }
        List<PieceDto> allPieces = boardDao.findAllPiecesByRoomName(gameDto.getRoomName());
        Map<Point, Piece> pointPieces = new HashMap<>();
        createBoard(pointPieces, allPieces);
        gameState = gameState.start(new Board(pointPieces), Color.convertColorByString(findGame.getTurnColor()));
        return new WebBoardDto((BoardAndTurnInfo) gameState.getResponse(), gameState.isRunnable());
    }

    public void createBoard(Map<Point, Piece> pointPieces, List<PieceDto> allPieces) {
        for (PieceDto piece : allPieces) {
            pointPieces.put(Point.of(piece.getPosition()), PieceConverter.convert(piece.getPieceType(), piece.getPieceColor()));
        }
        for (int i = LineNumber.MAX; i >= LineNumber.MIN; i--) {
            addEmptyPiecesEachLine(pointPieces, i);
        }
    }

    private void addEmptyPiecesEachLine(Map<Point, Piece> pointPieces, int i) {
        for (int j = LineNumber.MIN; j <= LineNumber.MAX; j++) {
            Point point = Point.of(i, j);
            if (!pointPieces.containsKey(point)) {
                pointPieces.put(point, Empty.getInstance());
            }
        }
    }

    public WebBoardDto move(MoveInfoDto moveInfo) {
        gameState = gameState.move(List.of(Point.of(moveInfo.getFrom()), Point.of(moveInfo.getTo())));
        boardDao.deleteByRoomNameAndPosition(moveInfo.getRoomName(), moveInfo.getTo());
        boardDao.update(moveInfo.getRoomName(), moveInfo.getFrom(), moveInfo.getTo());
        return new WebBoardDto((BoardAndTurnInfo) gameState.getResponse(), gameState.isRunnable());
    }

    public WebStatusDto status() {
        gameState = gameState.status();
        ScoreResponse response = (ScoreResponse) gameState.getResponse();
        if (response.getWhiteScore() > response.getBlackScore()) {
            return new WebStatusDto(response.getWhiteScore(),
                    response.getBlackScore(), Color.WHITE.toString());
        }
        if (response.getWhiteScore() < response.getBlackScore()) {
            return new WebStatusDto(response.getWhiteScore(),
                    response.getBlackScore(), Color.BLACK.toString());
        }
        return new WebStatusDto(response.getWhiteScore(),
                response.getBlackScore(), "DRAW");
    }

    public void finish() {
        gameState = gameState.finish();
    }

    public void deleteAndFinish(GameDto gameDto) {
        gameState = gameState.finish();
        gameDao.delete(gameDto.getRoomName());
    }
}
