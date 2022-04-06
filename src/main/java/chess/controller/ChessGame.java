package chess.controller;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.dto.PieceDto;
import chess.dto.ScoreDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessGame {
    private Board board;
    private final PieceDao pieceDao = new PieceDao();

    private static final Map<String, Function<Team, Piece>> PIECE_CREATE_STRATEGY =
            Map.of("Pawn", Pawn::new, "King", King::new, "Queen", Queen::new,
                    "Rook", Rook::new, "Knight", Knight::new, "Bishop", Bishop::new);
    private static final Map<String, Team> TEAM_CREATE_STRATEGY = Map.of("WHITE", WHITE, "BLACK", BLACK);

    public void start() {
        if (board != null) {
            throw new IllegalStateException("이미 진행중인 게임이 있습니다.");
        }
        initializeBoard();
        pieceDao.saveAll(board.getPieces());
        pieceDao.saveTurn(board.getTurn().name());
    }

    public void initializeBoard() {
        final String gameState = pieceDao.getGameState();
        final String turn = pieceDao.getTurn();
        if (gameState.equals("nothing")) {
            board = new Board();
            return;
        }
        initializeFromDateBase(turn);
    }

    private void initializeFromDateBase(final String currentTurn) {
        final Map<String, PieceDto> piecesFromDataBase = pieceDao.findAll();
        final Map<Position, Piece> pieces = new HashMap<>();

        for (String key : piecesFromDataBase.keySet()) {
            final Position position = Position.from(key);
            final Team team = TEAM_CREATE_STRATEGY.get(
                    piecesFromDataBase.get(key).getTeam());
            final String name = piecesFromDataBase.get(key)
                    .getName();
            pieces.put(position, PIECE_CREATE_STRATEGY.get(name).apply(team));
        }
        board = new Board(pieces, TEAM_CREATE_STRATEGY.get(currentTurn));
    }

    public void move(final String sourcePosition, final String targetPosition) {
        checkPlaying();
        board = board.movePiece(Position.from(sourcePosition), Position.from(targetPosition));
        pieceDao.updatePiece(targetPosition, board.getPieces().get(Position.from(targetPosition)));
        pieceDao.removeByPosition(sourcePosition);
        pieceDao.saveTurn(board.getTurn().name());
    }

    public Map<String, Object> getAllPiecesByPosition() {
        return pieceDao.findAll()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    public ScoreDto getScore() {
        checkPlaying();
        return new ScoreDto(board.getTotalPoint(WHITE), board.getTotalPoint(BLACK));
    }

    public void end() {
        checkPlaying();
        board = null;
        pieceDao.removeAllPieces();
        pieceDao.removeGameState();
    }

    private void checkPlaying() {
        if (board == null) {
            throw new IllegalStateException("진행 중인 게임이 없습니다.");
        }
    }
}
