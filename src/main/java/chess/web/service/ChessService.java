package chess.web.service;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.piece.Team;
import chess.web.dao.ChessGameDao;
import chess.web.dao.PieceDao;
import chess.web.dto.ChessGameDto;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;
    private ChessGame chessGame;

    public ChessService() {
        this.chessGameDao = new ChessGameDao();
        this.pieceDao = new PieceDao();
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

    public List<String> findByName(String gameName) {
        ChessGame selectedChessGame = chessGameDao.findByName(gameName);

        if (selectedChessGame == null) {
            return createChessBoard(gameName);
        }

        chessGame = selectedChessGame;

        return chessGame.getChessBoardSymbol();
    }

    private List<String> createChessBoard(String gameName) {
        this.chessGame = new ChessGame(gameName);

        chessGame.progress(Command.from("start"));

        return chessGame.getChessBoardSymbol();
    }

    public void save() {
        ChessGameDto chessGameDto = ChessGameDto.from(chessGame);

        String gameName = chessGameDto.getGameName();

        ChessGame chessGame = chessGameDao.findByName(gameName);

        if (chessGame == null) {
            updateChessGame(chessGameDto);
            return;
        }

        saveChessGame(chessGameDto);
    }

    private void updateChessGame(ChessGameDto chessGameDto) {
        chessGameDao.update(chessGameDto);
        pieceDao.update(chessGameDto);
    }

    private void saveChessGame(ChessGameDto chessGameDto) {
        chessGameDao.save(chessGameDto);
        pieceDao.save(chessGameDto);
    }

    public boolean isEnd() {
        return chessGame.isEnd();
    }
}
