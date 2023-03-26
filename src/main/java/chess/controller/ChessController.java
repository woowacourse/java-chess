package chess.controller;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

import chess.dao.ChessBoardDao;
import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.score.Score;
import chess.domain.Team;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.Optional;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameDao chessGameDao;
    private final ChessBoardDao chessBoardDao;


    public ChessController(InputView inputView, OutputView outputView,
                           ChessGameDao chessGameDao, ChessBoardDao chessBoardDao) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameDao = chessGameDao;
        this.chessBoardDao = chessBoardDao;
    }

    public void run() {
        outputView.printStartMessage();
        inputStartCommand();
        outputView.printGameList(chessGameDao.findAll());
        String gameCommand = inputGameCommand();
        ChessGame chessGame = startChessGame(gameCommand);
        progress(chessGame);
    }

    private ChessGame startChessGame(String gameCommand) {
        if (GameCommand.isNew(gameCommand)) {
            long chessGameId = chessGameDao.create();
            ChessGame chessGame = ChessGame.createGame(chessGameId);
            chessBoardDao.save(chessGameId, chessGame.getBoard());
            outputView.printGameId(chessGameId);
            return chessGame;
        }
        return chessGameDao.findById(Integer.parseInt(gameCommand));
    }

    private void inputStartCommand() {
        try {
            inputView.readStart();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            inputStartCommand();
        }
    }

    private String inputGameCommand() {
        try {
            return inputView.readGame();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return inputGameCommand();
        }
    }

    private void progress(ChessGame chessGame) {
        boolean onGoing = true;
        while (onGoing) {
            outputView.printCurrentTurn(chessGame.getTurn());
            outputView.printBoard(chessGame.getBoard());
            String command = inputCommand();
            play(chessGame, command);
            chessGameDao.updateTurn(chessGame);
            onGoing = !isGameOver(chessGame) && !GameCommand.isEnd(command);
        }
    }

    private String inputCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return inputCommand();
        }
    }

    private void play(ChessGame chessGame, String command) {
        if (GameCommand.isEnd(command)) {
            return;
        }
        if (GameCommand.isStatus(command)) {
            Map<Team, Score> scores = chessGame.getScoreAllTeam();
            Optional<Team> winner = chessGame.findWinner(scores);
            outputView.printStatus(scores, winner);
            return;
        }

        move(chessGame, command);
    }

    private void move(ChessGame chessGame, String command) {
        Position source = convertToSourcePosition(command);
        Position target = convertToTargetPosition(command);

        movePiece(chessGame, source, target);
    }

    private void movePiece(ChessGame chessGame, Position source, Position target) {
        try {
            Piece sourcePiece = chessGame.getBoard().getBoard().get(source);
            Piece targetPiece = chessGame.getBoard().getBoard().get(target);

            chessGame.movePiece(source, target);
            updateBoard(chessGame.getId(), source, target, sourcePiece, targetPiece);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
        }
    }

    private void updateBoard(long id, Position source, Position target, Piece sourcePiece, Piece targetPiece) {
        chessBoardDao.update(id, target, sourcePiece);
        if (!targetPiece.isSameTeam(Team.EMPTY)) {
            chessBoardDao.update(id, source, new Empty(Team.EMPTY));
            return;
        }
        chessBoardDao.update(id, source, targetPiece);
    }

    private boolean isGameOver(ChessGame chessGame) {
        if (chessGame.isOver()) {
            Map<Team, Score> scores = chessGame.getScoreAllTeam();
            Optional<Team> winner = chessGame.findWinner(scores);
            outputView.printWinner(winner);
            return true;
        }
        return false;
    }
}
