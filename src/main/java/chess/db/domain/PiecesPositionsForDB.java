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
import chess.db.dao.PiecePositionDAO;
import chess.db.dao.PositionDAO;
import chess.db.entity.PieceEntity;
import chess.db.entity.PositionEntity;
import chess.db.entity.PiecePositionEntity;
import chess.domain.player.type.TeamColor;
import chess.domain.position.type.File;
import java.util.ArrayList;
import java.util.List;

public class PiecesPositionsForDB {
    private final PiecePositionDAO piecePositionDAO;
    private final PieceDAO pieceDAO;
    private final PositionDAO positionDAO;

    public PiecesPositionsForDB() {
        piecePositionDAO = new PiecePositionDAO();
        pieceDAO = new PieceDAO();
        positionDAO = new PositionDAO();
    }

    public List<PiecePositionEntity> getInitialPiecesPositionsByColor(TeamColor teamColor) {
        List<PiecePositionEntity> initialPiecesPositions = new ArrayList<>();
        setInitialKings(teamColor, initialPiecesPositions);
        setInitialQueens(teamColor, initialPiecesPositions);
        setInitialRooks(teamColor, initialPiecesPositions);
        setInitialKnights(teamColor, initialPiecesPositions);
        setInitialBishops(teamColor, initialPiecesPositions);
        setInitialPawns(teamColor, initialPiecesPositions);
        return initialPiecesPositions;
    }

    private void setInitialKings(TeamColor teamColor,
        List<PiecePositionEntity> initialPiecesPositions) {
        PieceEntity kingEntity = pieceDAO.findByNameAndColor(KING, teamColor);
        if (teamColor == WHITE) {
            setInitialWhiteKingsPositions(kingEntity, initialPiecesPositions);
            return;
        }
        setInitialBlackKingsPositions(kingEntity, initialPiecesPositions);
    }

    private void setInitialWhiteKingsPositions(PieceEntity kingEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity whiteKingInitialPosition = positionDAO.findByFileAndRank(E, ONE);
        PiecePositionEntity piecePositionEntity
            = piecePositionDAO.save(
            new PiecePositionEntity(kingEntity, whiteKingInitialPosition)
        );
        initialPiecesPositions.add(piecePositionEntity);
    }

    private void setInitialBlackKingsPositions(PieceEntity kingEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity blackKingInitialPosition = positionDAO.findByFileAndRank(E, EIGHT);
        PiecePositionEntity piecePositionEntity
            = piecePositionDAO.save(
            new PiecePositionEntity(kingEntity, blackKingInitialPosition)
        );
        initialPiecesPositions.add(piecePositionEntity);
    }

    private void setInitialQueens(TeamColor teamColor,
        List<PiecePositionEntity> initialPiecesPositions) {
        PieceEntity queenEntity = pieceDAO.findByNameAndColor(QUEEN, teamColor);
        if (teamColor == WHITE) {
            setInitialWhiteQueensPositions(queenEntity, initialPiecesPositions);
            return;
        }
        setInitialBlackQueensPositions(queenEntity, initialPiecesPositions);
    }

    private void setInitialWhiteQueensPositions(PieceEntity queenEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity whiteQueenInitialPosition = positionDAO.findByFileAndRank(D, ONE);
        PiecePositionEntity piecePositionEntity
            = piecePositionDAO.save(
            new PiecePositionEntity(queenEntity, whiteQueenInitialPosition)
        );
        initialPiecesPositions.add(piecePositionEntity);
    }

    private void setInitialBlackQueensPositions(PieceEntity queenEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity blackQueenInitialPosition = positionDAO.findByFileAndRank(D, EIGHT);
        PiecePositionEntity piecePositionEntity = piecePositionDAO.save(
            new PiecePositionEntity(queenEntity, blackQueenInitialPosition)
        );
        initialPiecesPositions.add(piecePositionEntity);
    }

    private void setInitialRooks(TeamColor teamColor,
        List<PiecePositionEntity> initialPiecesPositions) {
        PieceEntity rookEntity = pieceDAO.findByNameAndColor(ROOK, teamColor);
        if (teamColor == WHITE) {
            setInitialWhiteRooksPositions(rookEntity, initialPiecesPositions);
            return;
        }
        setInitialBlackRooksPositions(rookEntity, initialPiecesPositions);
    }

    private void setInitialWhiteRooksPositions(PieceEntity rookEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity whiteRookInitialLeftPosition = positionDAO.findByFileAndRank(A, ONE);
        PositionEntity whiteRookInitialRightPosition = positionDAO.findByFileAndRank(H, ONE);
        PiecePositionEntity piecePositionEntity1
            = piecePositionDAO.save(
            new PiecePositionEntity(rookEntity, whiteRookInitialLeftPosition));
        PiecePositionEntity piecePositionEntity2
            = piecePositionDAO.save(
            new PiecePositionEntity(rookEntity, whiteRookInitialRightPosition));
        initialPiecesPositions.add(piecePositionEntity1);
        initialPiecesPositions.add(piecePositionEntity2);
    }

    private void setInitialBlackRooksPositions(PieceEntity rookEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity blackRookInitialLeftPosition = positionDAO.findByFileAndRank(A, EIGHT);
        PositionEntity blackRookInitialRightPosition = positionDAO.findByFileAndRank(H, EIGHT);
        PiecePositionEntity piecePositionEntity1
            = piecePositionDAO.save(
            new PiecePositionEntity(rookEntity, blackRookInitialLeftPosition));
        PiecePositionEntity piecePositionEntity2
            = piecePositionDAO.save(
            new PiecePositionEntity(rookEntity, blackRookInitialRightPosition));
        initialPiecesPositions.add(piecePositionEntity1);
        initialPiecesPositions.add(piecePositionEntity2);
    }

    private void setInitialKnights(TeamColor teamColor,
        List<PiecePositionEntity> initialPiecesPositions) {
        PieceEntity knightEntity = pieceDAO.findByNameAndColor(KNIGHT, teamColor);
        if (teamColor == WHITE) {
            setInitialWhiteKnightsPositions(knightEntity, initialPiecesPositions);
            return;
        }
        setInitialBlackKnightsPositions(knightEntity, initialPiecesPositions);

    }

    private void setInitialWhiteKnightsPositions(PieceEntity knightEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity whiteKnightInitialLeftPosition = positionDAO.findByFileAndRank(B, ONE);
        PositionEntity whiteKnightInitialRightPosition = positionDAO.findByFileAndRank(G, ONE);
        PiecePositionEntity piecePositionEntity1 = piecePositionDAO.save(
            new PiecePositionEntity(knightEntity, whiteKnightInitialLeftPosition)
        );
        PiecePositionEntity piecePositionEntity2 = piecePositionDAO.save(
            new PiecePositionEntity(knightEntity, whiteKnightInitialRightPosition)
        );
        initialPiecesPositions.add(piecePositionEntity1);
        initialPiecesPositions.add(piecePositionEntity2);
    }

    private void setInitialBlackKnightsPositions(PieceEntity knightEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity blackKnightInitialLeftPosition = positionDAO.findByFileAndRank(B, EIGHT);
        PositionEntity blackKnightInitialRightPosition = positionDAO.findByFileAndRank(G, EIGHT);
        PiecePositionEntity piecePositionEntity1 = piecePositionDAO.save(
            new PiecePositionEntity(knightEntity, blackKnightInitialLeftPosition)
        );
        PiecePositionEntity piecePositionEntity2 = piecePositionDAO.save(
            new PiecePositionEntity(knightEntity, blackKnightInitialRightPosition)
        );
        initialPiecesPositions.add(piecePositionEntity1);
        initialPiecesPositions.add(piecePositionEntity2);
    }

    private void setInitialBishops(TeamColor teamColor,
        List<PiecePositionEntity> initialPiecesPositions) {
        PieceEntity bishopEntity = pieceDAO.findByNameAndColor(BISHOP, teamColor);
        if (teamColor == WHITE) {
            setInitialWhiteBishopsPositions(bishopEntity, initialPiecesPositions);
            return;
        }
        setInitialBlackBishopsPositions(bishopEntity, initialPiecesPositions);

    }

    private void setInitialWhiteBishopsPositions(PieceEntity bishopEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity whiteBishopInitialLeftPosition = positionDAO.findByFileAndRank(C, ONE);
        PositionEntity whiteBishopInitialRightPosition = positionDAO.findByFileAndRank(F, ONE);
        PiecePositionEntity piecePositionEntity1 = piecePositionDAO.save(
            new PiecePositionEntity(bishopEntity, whiteBishopInitialLeftPosition)
        );
        PiecePositionEntity piecePositionEntity2 = piecePositionDAO.save(
            new PiecePositionEntity(bishopEntity, whiteBishopInitialRightPosition)
        );
        initialPiecesPositions.add(piecePositionEntity1);
        initialPiecesPositions.add(piecePositionEntity2);
    }

    private void setInitialBlackBishopsPositions(PieceEntity bishopEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        PositionEntity blackBishopInitialLeftPosition = positionDAO.findByFileAndRank(C, EIGHT);
        PositionEntity blackBishopInitialRightPosition = positionDAO.findByFileAndRank(F, EIGHT);
        PiecePositionEntity piecePositionEntity1 = piecePositionDAO.save(
            new PiecePositionEntity(bishopEntity, blackBishopInitialLeftPosition)
        );
        PiecePositionEntity piecePositionEntity2 = piecePositionDAO.save(
            new PiecePositionEntity(bishopEntity, blackBishopInitialRightPosition)
        );
        initialPiecesPositions.add(piecePositionEntity1);
        initialPiecesPositions.add(piecePositionEntity2);
    }

    private void setInitialPawns(TeamColor teamColor,
        List<PiecePositionEntity> initialPiecesPositions) {
        PieceEntity pawnEntity = pieceDAO.findByNameAndColor(PAWN, teamColor);
        if (teamColor == WHITE) {
            setInitialWhitePawnsPositions(pawnEntity, initialPiecesPositions);
            return;
        }
        setInitialBlackPawnsPositions(pawnEntity, initialPiecesPositions);
    }

    private void setInitialWhitePawnsPositions(PieceEntity pawnEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        for (File file : File.values()) {
            PositionEntity whitePawnInitialPosition = positionDAO.findByFileAndRank(file, TWO);
            PiecePositionEntity piecePositionEntity = piecePositionDAO.save(
                new PiecePositionEntity(pawnEntity, whitePawnInitialPosition));
            initialPiecesPositions.add(piecePositionEntity);
        }
    }

    private void setInitialBlackPawnsPositions(PieceEntity pawnEntity,
        List<PiecePositionEntity> initialPiecesPositions) {
        for (File file : File.values()) {
            PositionEntity blackPawnInitialPosition = positionDAO.findByFileAndRank(file, SEVEN);
            PiecePositionEntity piecePositionEntity = piecePositionDAO.save(
                new PiecePositionEntity(pawnEntity, blackPawnInitialPosition));
            initialPiecesPositions.add(piecePositionEntity);
        }
    }
}
