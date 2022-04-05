package chess.web.service;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.piece.Team;
import chess.web.dao.ChessBoardDao;
import chess.web.dao.ChessGameDao;
import chess.web.dto.ChessGameDto;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessGameDao chessGameDao;
    private final ChessBoardDao chessBoardDao;
    private ChessGame chessGame;

    public ChessService() {
        this.chessGameDao = new ChessGameDao();
        this.chessBoardDao = new ChessBoardDao();
    }

    public List<String> createChessBoard(String gameName) {
        this.chessGame = new ChessGame(gameName);

        chessGame.progress(Command.from("start"));

        return chessGame.getChessBoardSymbol();
    }

    public List<String> getCurrentChessBoard() {
        return chessGame.getChessBoardSymbol();
    }

    public List<String> move(String moveCommand) {
        Command command = Command.from(moveCommand);

        chessGame.progress(command);

        return chessGame.getChessBoardSymbol();
    }

    public Map<Team, Double> getScore() {
        return chessGame.calculateResult();
    }

    public String finish(Command command) {
        chessGame.progress(command);

        return chessGame.getWinTeamName();
    }

    public List<String> findAllByName(String gameName) {
        ChessGame selectedChessGame = chessGameDao.findAllByName(gameName);

        if (selectedChessGame == null) {
            chessGame = new ChessGame(gameName);
            chessGame.progress(Command.from("start"));

            return chessGame.getChessBoardSymbol();
        }

        chessGame = selectedChessGame;

        return chessGame.getChessBoardSymbol();
    }

    public void save() {
        ChessGameDto chessGameDto = new ChessGameDto(chessGame);

        String gameName = chessGameDto.getGameName();

        int chessboardId = chessGameDao.findIdByName(gameName);

        System.out.println("chessboardId = " + chessboardId);

        if (chessboardId > 0) {
            System.out.println("있으니까 여기 실행");
            chessGameDao.update(chessGameDto, chessboardId);
            return;
        }

        int savedId = chessBoardDao.save(chessGameDto);

        chessGameDao.save(chessGameDto, savedId);

        System.out.println("없으면 여기 실행");
    }

    public boolean isEnd() {
        return chessGame.isEnd();
    }
}
