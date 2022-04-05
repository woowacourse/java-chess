package web.service;

import chess.domain.board.Board;
import chess.domain.board.InitialBoardGenerator;
import chess.domain.board.Point;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.dto.*;
import web.dao.BoardDao;
import web.dao.GameDao;

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

    // 게임 입장 ->
    //          새로운 방을 만들 것인가?
    //          아니면 기존 방을 들어갈 것인가!
    // 게임 시작 ->
    //          게임 이어서 하도록!!
    //          새로운 게임 생성
    // 게임 말 이동로직
    // 게임 종료 로직 -> 게임 삭제할 것인가? 게임을 유지하고 종료만 할 것인가
}
