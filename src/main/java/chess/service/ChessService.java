package chess.service;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.dao.BoardDao;
import chess.domain.dao.BoardDaoImpl;
import chess.domain.dao.GameDao;
import chess.domain.dao.GameDaoImpl;
import chess.dto.BoardDto;
import chess.dto.GameDto;
import chess.dto.PieceDto;
import chess.domain.entity.Board;
import chess.domain.entity.Game;
import chess.domain.game.ChessGame;
import chess.domain.game.Status;
import chess.domain.state.Finished;
import chess.domain.state.Ready;
import chess.domain.state.Running;
import chess.domain.state.State;
import chess.domain.state.Turn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessService {

    private final GameDao gameDao;
    private final BoardDao boardDao;

    public ChessService() {
        this(new GameDaoImpl(), new BoardDaoImpl());
    }

    public ChessService(GameDao gameDao, BoardDao boardDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
    }

    public ChessGame createRoom() {
        final ChessGame chessGame = new ChessGame(new Ready());
        chessGame.start();

        final Long saveId = gameDao.save(GameDto.from(chessGame));

        boardDao.save(BoardDto.of(saveId, chessGame.board()));
        return chessGame;
    }

    public ChessGame load() {
        final Game game = gameDao.findGameByMaxId();
        final List<Board> boards = boardDao.findBoardById(game.getGame_id());

        final ChessBoard chessBoard = createBoard(boards);
        final State state = createState(game.getGame_state(), game.getGame_turn(), chessBoard);

        return new ChessGame(game.getGame_id(), state);
    }

    private ChessBoard createBoard(List<Board> boards) {
        final Map<Position, Piece> pieces = new HashMap<>();
        for (Board board : boards) {
            Position position = Position.valueOf(board.getBoard_position());
            Piece piece = createPiece(board.getBoard_piece(), board.getBoard_color(), position);

            pieces.put(position, piece);
        }

        return new ChessBoard(pieces);
    }

    private Piece createPiece(String board_piece, String board_color, Position position) {
        if (board_piece.equals("p")) {
            return new Pawn(PieceColor.valueOf(board_color), position);
        }
        if (board_piece.equals("r")) {
            return new Rook(PieceColor.valueOf(board_color), position);
        }
        if (board_piece.equals("n")) {
            return new Knight(PieceColor.valueOf(board_color), position);
        }
        if (board_piece.equals("b")) {
            return new Bishop(PieceColor.valueOf(board_color), position);
        }
        if (board_piece.equals("q")) {
            return new Queen(PieceColor.valueOf(board_color), position);
        }
        if (board_piece.equals("k")) {
            return new King(PieceColor.valueOf(board_color), position);
        }
        return new Blank(position);
    }

    private State createState(String state, String turn, ChessBoard chessBoard) {
        if (Objects.equals(state, "ready")) {
            return new Ready();
        }
        if (Objects.equals(state, "running")) {
            return new Running(chessBoard, Turn.from(turn));
        }
        return new Finished(chessBoard);
    }

    public ChessGame move(ChessGame chessGame, String source, String target) {
        final Position from = Position.valueOf(source);
        final Position to = Position.valueOf(target);
        final Long gameId = chessGame.getId();

        chessGame.move(from, to);

        boardDao.updateByPosition(gameId, from.getName(), new PieceDto(chessGame.board().findByPiece(from)));
        boardDao.updateByPosition(gameId, to.getName(), new PieceDto(chessGame.board().findByPiece(to)));
        gameDao.updateByGame(GameDto.of(gameId, chessGame.getState(), chessGame.turn().name()));

        return chessGame;
    }

    public Status status(ChessGame chessGame) {
        return chessGame.status();
    }

    public ChessGame end(ChessGame chessGame) {
        chessGame.end();
        final Long gameId = chessGame.getId();

        gameDao.updateByGame(GameDto.of(gameId, new Finished(chessGame.board()), PieceColor.NONE.getName()));
        return chessGame;
    }
}
