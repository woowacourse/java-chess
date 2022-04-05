package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.state.running.RunningBlack;
import chess.domain.game.state.running.RunningWhite;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.GameDto;
import chess.dto.TurnDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private final ChessGame chessGame;
    private final BoardDao chessDao;
    private final GameDao gameDao;

    public ChessService() {
        this.gameDao = new GameDao();
        this.chessDao = new BoardDao();
        this.chessGame = new ChessGame();
    }

    public GameDto getGame() {
        BoardDto boardDto = chessDao.loadBoard();
        TurnDto turnDto = gameDao.getTurn();

        if (PieceColor.BLACK.name().equals(turnDto.getTurn())) {
            chessGame.startsWith(new RunningBlack(convertBoardDtoToBoard(boardDto)));
            return new GameDto(boardDto.getBoard(), turnDto.getTurn());
        }
        chessGame.startsWith(new RunningWhite(convertBoardDtoToBoard(boardDto)));
        return new GameDto(boardDto.getBoard(), turnDto.getTurn());
    }

    public Board convertBoardDtoToBoard(BoardDto boardDto) {
        Map<String, List<String>> rawBoard = boardDto.getBoard();
        Map<Position, AbstractPiece> board = new HashMap<>();
        for (String key : rawBoard.keySet()) {
            Position position = Position.from(key);
            List<String> strings = rawBoard.get(key);
            String piece = strings.get(0);
            String color = strings.get(1);
            System.out.println(piece);
            System.out.println(color);
            if ("PAWN".equals(piece)) {
                board.put(position, new Pawn(PieceColor.getPieceColor(color)));
            }
            if ("ROOK".equals(piece)) {
                board.put(position, new Rook(PieceColor.getPieceColor(color)));
            }
            if ("KNIGHT".equals(piece)) {
                board.put(position, new Knight(PieceColor.getPieceColor(color)));
            }
            if ("BISHOP".equals(piece)) {
                board.put(position, new Bishop(PieceColor.getPieceColor(color)));
            }
            if ("KING".equals(piece)) {
                board.put(position, new King(PieceColor.getPieceColor(color)));
            }
            if ("QUEEN".equals(piece)) {
                board.put(position, new Queen(PieceColor.getPieceColor(color)));
            }
        }
        return new Board(board);
    }
}
