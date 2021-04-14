package chess.service;

import chess.domain.Game;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.command.Command;
import chess.domain.command.Move;
import chess.domain.dao.PieceDao;
import chess.domain.dao.PlayerDao;
import chess.domain.dao.TurnDao;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PlayerDto;
import chess.domain.dto.TurnDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebChessService {
    private final PieceDao pieceDao;
    private final TurnDao turnDao;
    private final PlayerDao playerDao;

    public WebChessService() {
        this.pieceDao = new PieceDao();
        this.turnDao = new TurnDao();
        this.playerDao = new PlayerDao();
    }

    public Map<Position, Piece> loadBoard(int index) {
        Map<Position, Piece> loadBoard = new HashMap<>();
        for (XPosition xPosition : XPosition.values()) {
            putPieceOnXPosition(loadBoard, xPosition, index);
        }
        return loadBoard;
    }

    private void putPieceOnXPosition(Map<Position, Piece> loadBoard,
        XPosition xPosition, int index){
        for (YPosition yPosition : YPosition.values()) {
            Position position = Position.of(xPosition, yPosition);
            PieceDto pieceDTO = pieceDao.pieceOnLocation(position.symbol(), index);
            String rawKind = pieceDTO.getPieceKind();
            Piece piece = new Piece(rawKind);
            loadBoard.put(position, piece);
        }
    }

    public void initiateGame(int index) {
        Game game = new Game();
        game.init();
        turnDao.initTurn(index);

        Board board = game.getBoard();
        saveInitialBoard(board, index);
    }

    private void saveInitialBoard(Board board,int index) {
        Map<Position, Piece> rawBoard = board.recentBoard();
        pieceDao.resetPiece(index);
        for (Map.Entry<Position, Piece> elem : rawBoard.entrySet()) {
            Position position = elem.getKey();
            Piece piece = elem.getValue();
            PieceDto pieceDTO = processedPieceDTO(position, piece);
            pieceDao.addPiece(pieceDTO, index);
        }
    }

    private PieceDto processedPieceDTO(Position position, Piece piece) {
        return new PieceDto(position.symbol(), piece.symbol());
    }

    public Map<String, String> move(String moveRawCommand, int index) {
        Map<String, String> moveResult = new HashMap<>();
        Game game = loadGame(index);
        String movePieceResult = movePieceResult(game, moveRawCommand, index);
        String winner = winner(game, index);
        moveResult.put("isSuccess", movePieceResult);
        moveResult.put("winner", winner);
        moveResult.put("turn", getTurn(index));
        return moveResult;
    }

    private String movePieceResult(Game game, String moveRawCommand, int index) {
        Move move = new Move();
        try {
            Command command = move.run(game, moveRawCommand);
            moveOnDB(moveRawCommand, index);
            return "Success";
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }
    }

    private String winner(Game game, int index) {
        if (game.isEnd()) {
            PieceColor winnerColor = game.winnerColor();
            end(index);
            return winnerColor.getName();
        }
        return "None";
    }

    public void end(int index) {
        pieceDao.resetPiece(index);
    }

    public String getTurn(int index) {
        TurnDto turnDTO = turnDao.loadTurnDTO(index);
        PieceColor pieceColor = turnDTO.getPieceColor();
        return pieceColor.name();
    }

    public Map<String, String> scores(int index) {
        Game game = loadGame(index);
        String whitePoint = String.valueOf(game.computeWhitePoint());
        String blackPoint = String.valueOf(game.computeBlackPoint());

        Map<String, String> scoreMap = new HashMap<>();
        scoreMap.put("whiteScore", whitePoint);
        scoreMap.put("blackScore", blackPoint);
        return scoreMap;
    }

    public Game loadGame(int index) {
        String rawTurnColor = getTurn(index);
        PieceColor turnColor = PieceColor.translateTurnColor(rawTurnColor);
        Game game = new Game();
        game.loadGame(loadBoard(index), turnColor);
        return game;
    }

    public void moveOnDB(String rawMoveCommand, int index) {
        List<String> moveSourceTarget = Arrays.asList(rawMoveCommand.split(" "));
        PieceDto pieceDTO = pieceDao.pieceOnLocation(moveSourceTarget.get(1),index);
        pieceDTO.setLocation(moveSourceTarget.get(2));
        PieceDto voidPiece = new PieceDto(moveSourceTarget.get(1), ".");
        pieceDao.deletePiece(moveSourceTarget.get(1),index);
        pieceDao.deletePiece(moveSourceTarget.get(2),index);
        pieceDao.addPiece(pieceDTO, index);
        pieceDao.addPiece(voidPiece, index);
        switchTurn(index);
    }

    private void switchTurn(int index) {
        PieceColor thisColor = PieceColor.translateTurnColor(getTurn(index));
        String switchTurnColor = thisColor.oppositeColor().name();
        turnDao.updateTurn(switchTurnColor, index);
    }

    public boolean addPlayer(String rawPlayer) {
        boolean checkExistedPlayerResult = checkExistedPlayer(rawPlayer);
        if (!checkExistedPlayerResult) {
            playerDao.addPlayer(rawPlayer);
        }
        return !checkExistedPlayerResult;
    }

    private boolean checkExistedPlayer(String rawPlayer) {
        return playerDao.isExistedPlayer(rawPlayer);
    }

    public List<PlayerDto> loadPlayers() {
        return playerDao.allPlayers();
    }
}
