package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.state.running.RunningBlack;
import chess.domain.game.state.running.RunningWhite;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.dto.GameDto;
import chess.dto.ScoreDto;
import chess.dto.TurnDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessGame chessGame;
    private final BoardDao boardDao;
    private final GameDao gameDao;

    public ChessService(BoardDao boardDao, GameDao gameDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
        this.chessGame = new ChessGame();
    }

    public GameDto getGame() {
        BoardDto boardDto = boardDao.loadBoard();
        TurnDto turnDto = gameDao.getTurn();

        if (PieceColor.BLACK.name().equals(turnDto.getTurn())) {
            chessGame.updateState(new RunningBlack(convertBoardDtoToBoard(boardDto)));
            return new GameDto(boardDto.getBoard(), turnDto.getTurn());
        }
        chessGame.updateState(new RunningWhite(convertBoardDtoToBoard(boardDto)));
        return new GameDto(boardDto.getBoard(), turnDto.getTurn());
    }

    private Board convertBoardDtoToBoard(BoardDto boardDto) {
        Map<String, List<String>> rawBoard = boardDto.getBoard();
        Map<Position, AbstractPiece> board = new HashMap<>();
        for (String key : rawBoard.keySet()) {
            Position position = Position.from(key);
            List<String> strings = rawBoard.get(key);
            String piece = strings.get(0);
            String color = strings.get(1);
            board.put(position, PieceFactory.getPiece(color + piece));
        }
        return new Board(board);
    }


    public void movePiece(CommandDto commandDto) {
        Position from = Position.from(commandDto.getFrom());
        Position to = Position.from(commandDto.getTo());
        chessGame.movePiece(from, to);
        boardDao.updatePiecePosition(commandDto);
        gameDao.changeTurn();
    }

    public void endGame() {
        chessGame.endGame();
    }

    public ScoreDto getScore() {
        return new ScoreDto(chessGame.getScore());
    }
}
