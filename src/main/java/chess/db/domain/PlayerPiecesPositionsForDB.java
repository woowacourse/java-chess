package chess.db.domain;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;
import static chess.domain.player.type.TeamColor.WHITE;
import static chess.domain.position.type.File.A;
import static chess.domain.position.type.File.B;
import static chess.domain.position.type.File.C;
import static chess.domain.position.type.File.D;
import static chess.domain.position.type.File.E;
import static chess.domain.position.type.File.F;
import static chess.domain.position.type.File.G;
import static chess.domain.position.type.File.H;
import static chess.domain.position.type.Rank.EIGHT;
import static chess.domain.position.type.Rank.ONE;
import static chess.domain.position.type.Rank.SEVEN;
import static chess.domain.position.type.Rank.TWO;

import chess.db.dao.PieceDAO;
import chess.db.dao.PlayerPiecePositionDAO;
import chess.db.dao.PositionDAO;
import chess.db.entity.PieceEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePositionEntity;
import chess.db.entity.PositionEntity;
import chess.domain.position.type.File;
import java.sql.SQLException;

public class PlayerPiecesPositionsForDB {
    private final PlayerPiecePositionDAO playerPiecePositionDAO;
    private final PieceDAO pieceDAO;
    private final PositionDAO positionDAO;

    public PlayerPiecesPositionsForDB() {
        playerPiecePositionDAO = new PlayerPiecePositionDAO();
        pieceDAO = new PieceDAO();
        positionDAO = new PositionDAO();
    }

    public void setInitialPiecesPositionsOfPlayer(PlayerEntity playerEntity)
        throws SQLException {
        setInitialKings(playerEntity);
        setInitialQueens(playerEntity);
        setInitialRooks(playerEntity);
        setInitialKnights(playerEntity);
        setInitialBishops(playerEntity);
        setInitialPawns(playerEntity);
    }

    private void setInitialKings(PlayerEntity playerEntity) throws SQLException {
        PieceEntity kingEntity
            = pieceDAO.findByPieceTypeAndTeamColor(KING, playerEntity.getTeamColor());
        if (playerEntity.getTeamColor() == WHITE) {
            setInitialWhiteKingPosition(kingEntity, playerEntity);
            return;
        }
        setInitialBlackKingPosition(kingEntity, playerEntity);
    }

    private void setInitialWhiteKingPosition(PieceEntity kingEntity, PlayerEntity playerEntity)
        throws SQLException {
        PositionEntity position = positionDAO.findByFileAndRank(E, ONE);
        playerPiecePositionDAO.save(
            new PlayerPiecePositionEntity(playerEntity, kingEntity, position));
    }

    private void setInitialBlackKingPosition(PieceEntity kingEntity, PlayerEntity playerEntity)
        throws SQLException {
        PositionEntity position = positionDAO.findByFileAndRank(E, EIGHT);
        playerPiecePositionDAO.save(
            new PlayerPiecePositionEntity(playerEntity, kingEntity, position));
    }

    private void setInitialQueens(PlayerEntity playerEntity) throws SQLException {
        PieceEntity queenEntity = pieceDAO.findByPieceTypeAndTeamColor(QUEEN,
            playerEntity.getTeamColor());
        if (playerEntity.getTeamColor() == WHITE) {
            setInitialWhiteQueensPositions(queenEntity, playerEntity);
            return;
        }
        setInitialBlackQueensPositions(queenEntity, playerEntity);
    }

    private void setInitialWhiteQueensPositions(PieceEntity queenEntity, PlayerEntity playerEntity)
        throws SQLException {
        PositionEntity position = positionDAO.findByFileAndRank(D, ONE);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, queenEntity, position));
    }

    private void setInitialBlackQueensPositions(PieceEntity queenEntity, PlayerEntity playerEntity)
        throws SQLException {
        PositionEntity position = positionDAO.findByFileAndRank(D, EIGHT);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, queenEntity, position));
    }

    private void setInitialRooks(PlayerEntity playerEntity) throws SQLException {
        PieceEntity rookEntity
            = pieceDAO.findByPieceTypeAndTeamColor(ROOK, playerEntity.getTeamColor());
        if (playerEntity.getTeamColor() == WHITE) {
            setInitialWhiteRooksPositions(rookEntity, playerEntity);
            return;
        }
        setInitialBlackRooksPositions(rookEntity, playerEntity);
    }

    private void setInitialWhiteRooksPositions(PieceEntity rookEntity, PlayerEntity playerEntity)
        throws SQLException {
        PositionEntity leftPosition = positionDAO.findByFileAndRank(A, ONE);
        PositionEntity rightPosition = positionDAO.findByFileAndRank(H, ONE);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, rookEntity, leftPosition));
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, rookEntity, rightPosition));
    }

    private void setInitialBlackRooksPositions(PieceEntity rookEntity, PlayerEntity playerEntity)
        throws SQLException {
        PositionEntity leftPosition = positionDAO.findByFileAndRank(A, EIGHT);
        PositionEntity rightPosition = positionDAO.findByFileAndRank(H, EIGHT);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, rookEntity, leftPosition));
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, rookEntity, rightPosition));
    }

    private void setInitialKnights(PlayerEntity playerEntity) throws SQLException {
        PieceEntity knightEntity
            = pieceDAO.findByPieceTypeAndTeamColor(KNIGHT, playerEntity.getTeamColor());
        if (playerEntity.getTeamColor() == WHITE) {
            setInitialWhiteKnightsPositions(knightEntity, playerEntity);
            return;
        }
        setInitialBlackKnightsPositions(knightEntity, playerEntity);

    }

    private void setInitialWhiteKnightsPositions(PieceEntity knightEntity,
        PlayerEntity playerEntity) throws SQLException {
        PositionEntity leftPosition = positionDAO.findByFileAndRank(B, ONE);
        PositionEntity rightPosition = positionDAO.findByFileAndRank(G, ONE);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, knightEntity, leftPosition));
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, knightEntity, rightPosition));
    }

    private void setInitialBlackKnightsPositions(PieceEntity knightEntity,
        PlayerEntity playerEntity) throws SQLException {
        PositionEntity leftPosition = positionDAO.findByFileAndRank(B, EIGHT);
        PositionEntity rightPosition = positionDAO.findByFileAndRank(G, EIGHT);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, knightEntity, leftPosition));
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, knightEntity, rightPosition));
    }

    private void setInitialBishops(PlayerEntity playerEntity) throws SQLException {
        PieceEntity bishopEntity
            = pieceDAO.findByPieceTypeAndTeamColor(BISHOP, playerEntity.getTeamColor());
        if (playerEntity.getTeamColor() == WHITE) {
            setInitialWhiteBishopsPositions(bishopEntity, playerEntity);
            return;
        }
        setInitialBlackBishopsPositions(bishopEntity, playerEntity);

    }

    private void setInitialWhiteBishopsPositions(PieceEntity bishopEntity,
        PlayerEntity playerEntity) throws SQLException {
        PositionEntity leftPosition = positionDAO.findByFileAndRank(C, ONE);
        PositionEntity rightPosition = positionDAO.findByFileAndRank(F, ONE);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, bishopEntity, leftPosition));
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, bishopEntity, rightPosition));
    }

    private void setInitialBlackBishopsPositions(PieceEntity bishopEntity,
        PlayerEntity playerEntity) throws SQLException {
        PositionEntity leftPosition = positionDAO.findByFileAndRank(C, EIGHT);
        PositionEntity rightPosition = positionDAO.findByFileAndRank(F, EIGHT);
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, bishopEntity, leftPosition));
        playerPiecePositionDAO
            .save(new PlayerPiecePositionEntity(playerEntity, bishopEntity, rightPosition));
    }

    private void setInitialPawns(PlayerEntity playerEntity) throws SQLException {
        PieceEntity pawnEntity
            = pieceDAO.findByPieceTypeAndTeamColor(PAWN, playerEntity.getTeamColor());
        if (playerEntity.getTeamColor() == WHITE) {
            setInitialWhitePawnsPositions(pawnEntity, playerEntity);
            return;
        }
        setInitialBlackPawnsPositions(pawnEntity, playerEntity);
    }

    private void setInitialWhitePawnsPositions(PieceEntity pawnEntity, PlayerEntity playerEntity)
        throws SQLException {
        for (File file : File.values()) {
            PositionEntity position = positionDAO.findByFileAndRank(file, TWO);
            playerPiecePositionDAO
                .save(new PlayerPiecePositionEntity(playerEntity, pawnEntity, position));
        }
    }

    private void setInitialBlackPawnsPositions(PieceEntity pawnEntity, PlayerEntity playerEntity)
        throws SQLException {
        for (File file : File.values()) {
            PositionEntity position = positionDAO.findByFileAndRank(file, SEVEN);
            playerPiecePositionDAO
                .save(new PlayerPiecePositionEntity(playerEntity, pawnEntity, position));
        }
    }
}
