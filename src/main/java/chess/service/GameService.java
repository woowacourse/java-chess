package chess.service;

import chess.dao.*;
import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Result;
import chess.domain.ResultCalculator;
import chess.domain.piece.Piece;
import chess.dto.GameDto;
import chess.dto.NavigatorDto;
import chess.dto.PieceDto;
import chess.dto.UserDto;
import spark.Request;

import java.util.List;
import java.util.Map;

public class GameService {
    private static final int EMPTY_SIZE = 0;
    private static GameDao gameDao = GameDaoImpl.getInstance();
    private static UserDao userDao = UserDaoImpl.getInstance();
    private static PieceDao pieceDao = PieceDaoImpl.getInstance();

    private GameService() {}

    public static List<GameDto> getNotEndGames() {
        return gameDao.findNotEndGames();
    }

    public static int addGame() {
        return gameDao.addGame();
    }

    public static GameDto findById(int gameId) {
        return gameDao.findById(gameId);
    }

    public static void updateGame(GameDto gameDto) {
        gameDao.updateGame(gameDto);
    }

    public static int startGame(Request req){
        int gameId = GameService.addGame();
        UserDto whiteUserDto = new UserDto(gameId, req.queryParams("white_user"), 2);
        UserDto blackUserDto = new UserDto(gameId, req.queryParams("black_user"), 1);
        userDao.addUser(whiteUserDto);
        userDao.addUser(blackUserDto);
        return gameId;
    }

    public static Board setBoard(Board board, int gameId) {
        List<PieceDto> pieceDtos = findPiecesByGameId(gameId);
        if (pieceDtos.size() == EMPTY_SIZE) {
            board.initBoard();
            Map<Position, Piece> pieces = board.getPieces();

            for (Position position : pieces.keySet()) {
                int teamId = pieces.get(position).getAliance().getTeamId();
                int kindId = pieces.get(position).getPieceValue().getKindId();
                PieceDto pieceDto = new PieceDto(teamId, gameId, kindId, position.toString());
                pieceDao.addPiece(pieceDto);
            }
        }

        if (pieceDtos.size() != EMPTY_SIZE) {
            for (PieceDto piece : pieceDtos) {
                board.putPiece(piece.getPosition(), piece.getAliance().getTeamId(), piece.getKindId());
            }
        }
        return board;
    }

    public static void judgeEndGame(Board board, GameDto gameDto){
        if (gameDto.isEnd()) {
            ResultCalculator resultCalculator = new ResultCalculator(board);
            Result result = new Result(resultCalculator.calculateResult());
            throw new RuntimeException(String.format("[최종 스코어] 백 : 흑 = %.1f : %.1f 게임이 종료되었습니다.",
                    result.getWhiteResult(), result.getBlackResult()));
        }
    }

    public static List<UserDto> findUsersByGameId(int gameId) {
        return userDao.findByGameId(gameId);
    }

    public static List<PieceDto> findPiecesByGameId(int gameId) {
        return pieceDao.findByGameId(gameId);
    }

    public static void updatePiece(NavigatorDto navigatorDto) {
        pieceDao.updatePiece(navigatorDto);
    }
}
