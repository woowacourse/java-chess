package chess.service;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.command.Command;
import chess.domain.command.Move;
import chess.domain.dao.PieceDao;
import chess.domain.dao.TurnDao;
import chess.domain.dto.PieceDTO;
import chess.domain.dto.TurnDTO;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebChessService {
    private final PieceDao pieceDao;
    private final TurnDao turnDao;

    public WebChessService() {
        this.pieceDao = new PieceDao();
        this.turnDao = new TurnDao();
    }

    public Map<Position, Piece> loadBoard() {
        Map<Position, Piece> loadBoard = new HashMap<>();
        for (XPosition xPosition : XPosition.values()) {
            putPieceOnXPosition(loadBoard, xPosition);
        }
        return loadBoard;
    }

    private void putPieceOnXPosition(Map<Position, Piece> loadBoard,
        XPosition xPosition){
        for (YPosition yPosition : YPosition.values()) {
            Position position = Position.of(xPosition, yPosition);
            PieceDTO pieceDTO = pieceDao.pieceOnLocation(position.symbol(), 1);
            String rawKind = pieceDTO.getPieceKind();
            Piece piece = new Piece(rawKind);
            loadBoard.put(position, piece);
        }
    }

    public void initiateGame() {
        Game game = new Game();
        game.init();
        turnDao.initTurn();

        Board board = game.getBoard();
        saveInitialBoard(board);
    }

    private void saveInitialBoard(Board board) {
        Map<Position, Piece> rawBoard = board.recentBoard();
        pieceDao.resetPiece(1);
        for (Map.Entry<Position, Piece> elem : rawBoard.entrySet()) {
            Position position = elem.getKey();
            Piece piece = elem.getValue();
            PieceDTO pieceDTO = processedPieceDTO(position, piece);
            pieceDao.addPiece(pieceDTO);
        }
    }

    private PieceDTO processedPieceDTO(Position position, Piece piece) {
        return new PieceDTO(position.symbol(), piece.symbol());
    }

    public Map<String, String> move(String moveRawCommand) {
        Map<String, String> moveResult = new HashMap<>();
        Game game = loadGame();
        String movePieceResult = movePieceResult(game, moveRawCommand);
        String winner = winner(game);
        moveResult.put("isSuccess", movePieceResult);
        moveResult.put("winner", winner);
        moveResult.put("turn", getTurn());
        return moveResult;
    }

    private String movePieceResult(Game game, String moveRawCommand) {
        Move move = new Move();
        try {
            Command command = move.run(game, moveRawCommand);
            moveOnDB(moveRawCommand);
            return "Success";
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }
    }

    private String winner(Game game) {
        if (game.isEnd()) {
            PieceColor winnerColor = game.winnerColor();
            end();
            return winnerColor.getName();
        }
        return "None";
    }

    public void end() {
        pieceDao.resetPiece(1);
    }

    public String getTurn() {
        TurnDTO turnDTO = turnDao.loadTurnDTO(1);
        PieceColor pieceColor = turnDTO.getPieceColor();
        return pieceColor.name();
    }

    public Map<String, String> scores() {
        Game game = loadGame();
        String whitePoint = String.valueOf(game.computeWhitePoint());
        String blackPoint = String.valueOf(game.computeBlackPoint());

        Map<String, String> scoreMap = new HashMap<>();
        scoreMap.put("whiteScore", whitePoint);
        scoreMap.put("blackScore", blackPoint);
        return scoreMap;
    }

    public Game loadGame() {
        String rawTurnColor = getTurn();
        PieceColor turnColor = PieceColor.translateTurnColor(rawTurnColor);
        Game game = new Game();
        game.loadGame(loadBoard(), turnColor);
        return game;
    }

    public void moveOnDB(String rawMoveCommand) {
        List<String> moveSourceTarget = Arrays.asList(rawMoveCommand.split(" "));
        PieceDTO pieceDTO = pieceDao.pieceOnLocation(moveSourceTarget.get(1),1);
        pieceDTO.setLocation(moveSourceTarget.get(2));
        PieceDTO voidPiece = new PieceDTO(moveSourceTarget.get(1), ".");
        pieceDao.deletePiece(moveSourceTarget.get(1),1);
        pieceDao.deletePiece(moveSourceTarget.get(2),1);
        pieceDao.addPiece(pieceDTO);
        pieceDao.addPiece(voidPiece);
        switchTurn();
    }

    private void switchTurn() {
        PieceColor thisColor = PieceColor.translateTurnColor(getTurn());
        String switchTurnColor = thisColor.oppositeColor().name();
        turnDao.updateTurn(switchTurnColor);
    }
}
