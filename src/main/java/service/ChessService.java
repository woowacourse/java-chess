package service;

import chess.command.MoveCommand;
import chess.game.ChessGame;
import chess.location.Location;
import chess.progress.Progress;
import com.google.gson.Gson;
import converter.ChessGameConverter;
import dao.BoardDao;
import dao.ChessGameDao;
import dao.ChessGamesDao;
import dao.PieceDao;
import dto.*;
import spark.Request;
import vo.PieceVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessService {
    private static final ChessGamesDao chessGamesDao = new ChessGamesDao();
    private static final PieceDao pieceDao = new PieceDao();
    private static final ChessGameDao chessGameDao = new ChessGameDao();
    private static final BoardDao boardDao = new BoardDao();
    private static final Gson GSON = new Gson();
    private static final int GAME_ID = 1;

    public String findAllBoards() throws SQLException {
        ArrayList<ChessGameDto> all = chessGamesDao.findAll();
        ChessGamesDto chessGamesDto = new ChessGamesDto(all);
        return GSON.toJson(chessGamesDto);
    }

    public String findBoard(int boardId) throws SQLException {
        List<PieceVo> pieceVos = pieceDao.findAll(boardId);

        ChessGameDto chessGameDto = chessGameDao.findChessGameBy(boardId);

        if (pieceVos == null) {
            BoardDto boardDto = new BoardDto(new ChessGame().getChessBoard());
            return GSON.toJson(boardDto);
        }

        BoardDto boardDto = new BoardDto(ChessGameConverter.convert(pieceVos, chessGameDto).getChessBoard());
        return GSON.toJson(boardDto);
    }

    public String move(LocationDto nowDto,LocationDto destinationDto, ChessGame chessGame) {
        Location nowLocation = new Location(nowDto.getRow(), nowDto.getCol());
        Location destinationLocation =
                new Location(destinationDto.getRow(), destinationDto.getCol());

        MoveCommand move = MoveCommand.of(nowLocation, destinationLocation, chessGame);

        Progress progress = chessGame.doOneCommand(move);

        changeTurnIfMoved(chessGame, progress);

        ChessMoveDto chessMoveDto = new ChessMoveDto(
                new ChessGameScoresDto(chessGame.calculateScores())
                , progress
                , chessGame.getTurn());

        return GSON.toJson(chessMoveDto);
    }

    private static void changeTurnIfMoved(ChessGame chessGame, Progress progress) {
        if (Progress.CONTINUE == progress) {
            chessGame.changeTurn();
        }
    }

    public String findWinner(ChessGame chessGame) {
        ChessResultDto chessResultDto = new ChessResultDto(chessGame.findWinner());
        return GSON.toJson(chessResultDto);
    }

    public void insertChessBoard(ChessGame chessGame) throws SQLException {
        boardDao.addBoard(chessGame.getChessBoard(), GAME_ID);
    }
}
