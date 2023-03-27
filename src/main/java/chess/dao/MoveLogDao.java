package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMaker;
import chess.domain.position.Position;
import chess.domain.team.Team;
import chess.dto.ChessGameDto;
import chess.dto.ChessPositionDto;
import chess.dto.MoveLogDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveLogDao {

    public static void insertMove(final ChessGameDto chessGameDto,
                                  final ChessPositionDto sourcePositionDto,
                                  final ChessPositionDto targetPositionDto) {
        final var query = "INSERT INTO " +
                "move_log(chess_game_id, source_position_id, target_position_id) " +
                "VALUES(?, ?, ?)";

        JdbcTemplate.executeQuery(query, chessGameDto.getId(), sourcePositionDto.getId(), targetPositionDto.getId());
    }

    public static Board load(final ChessGameDto chessGameDto, final Board board) {
        final Map<Position, Piece> loadBoard = new HashMap<>(board.getBoard());

        for (MoveLogDto moveLogDto : findAll(chessGameDto)) {
            ChessPositionDto sourcePositionDto = ChessPositionDao.findById(moveLogDto.getSourcePositionId());
            ChessPositionDto targetPositionDto = ChessPositionDao.findById(moveLogDto.getTargetPositionId());

            final Position sourcePosition = Position.of(sourcePositionDto.getFile(), sourcePositionDto.getRank());
            final Position targetPosition = Position.of(targetPositionDto.getFile(), targetPositionDto.getRank());
            final Piece sourcePiece = PieceMaker.bind(sourcePositionDto.getPieceName(), sourcePositionDto.getTeamName());

            loadBoard.put(targetPosition, sourcePiece);
            loadBoard.put(sourcePosition, new Empty(Team.NONE));
        }

        return new Board(loadBoard);
    }

    private static List<MoveLogDto> findAll(final ChessGameDto chessGameDto) {
        final var query = "SELECT * FROM move_log WHERE chess_game_id = ?";

        final RowMapper<List<MoveLogDto>> mapper = resultSet -> {
            List<MoveLogDto> moveLogDtos = new ArrayList<>();
            while (resultSet.next()) {
                moveLogDtos.add(MoveLogDto.of(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                ));
            }
            return moveLogDtos;
        };

        return JdbcTemplate.select(query, mapper, chessGameDto.getId());
    }
}
