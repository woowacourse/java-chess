package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.game.File;
import chess.domain.game.PieceMapper;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.game.Turn;
import chess.domain.gameFactory.FakeGameFactory;
import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.dto.game.ChessGameLoadDto;
import chess.service.ChessGameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.PositionFixtures.A1;
import static chess.domain.PositionFixtures.A2;
import static org.assertj.core.api.Assertions.assertThat;

class DbChessGameDaoTest {

    private final ChessDao dao = new DbChessGameDao();
    private final ChessGameService chessGameService = new ChessGameService(dao);
    private ChessGame chessGame;

    @BeforeEach
    void init() {
        dao.delete();
        chessGame = new FakeGameFactory().generate();
        chessGameService.save(chessGame);
    }

    @Test
    void name() {
        final ChessGameLoadDto dto = dao.loadGame();
        final ChessGame parsedChessGame = parse(dto);
        final Map<Position, Piece> board = parsedChessGame.getBoard();
        final Map<Position, Piece> result = board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSamePieceTypeAs(PieceType.ROOK) ||
                        entry.getValue().isSamePieceTypeAs(PieceType.BISHOP))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertThat(result).containsExactly(
                Map.entry(A2, Bishop.instance(Team.WHITE)),
                Map.entry(A1, Rook.instance(Team.WHITE)));
    }

    private ChessGame parse(final ChessGameLoadDto dto) {
        final Map<Position, Piece> board = new HashMap<>();
        final List<String> pieceType = dto.getPieceTypes();
        final List<String> pieceFiles = dto.getPieceFiles();
        final List<String> pieceRanks = dto.getPieceRanks();
        final List<String> pieceTeams = dto.getPieceTeams();
        for (int i = 0; i < dto.getPieceRanks().size(); i++) {
            final Position position = Position.of(File.valueOf(pieceFiles.get(i)), Rank.valueOf(pieceRanks.get(i)));
            final Piece piece = PieceMapper.get(PieceType.valueOf(pieceType.get(i)), Team.valueOf(pieceTeams.get(i)));
            board.put(position, piece);
        }
        final String lastTurn = dto.getLastTurn();
        return ChessGame.of(board, Turn.of(Team.valueOf(lastTurn)));
    }
}
