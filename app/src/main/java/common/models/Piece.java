package common.models;

import common.logic.CommonRule;
import common.logic.LegalMove;
import common.logic.WinCondition;
import common.moves.Move;
import common.results.MoveResult;

import java.util.List;
import java.util.Objects;

public class Piece{
    private final String pieceName;
    private final Coordinate initialSquare;
    private final List<Move> movements;
    private final List<Move> eatMovements;
    private final SideColor color;
    private final Boolean isImportant;
    private final int id;

    public Piece(String pieceName, Coordinate initialSquare, List<Move> movements, List<Move> eatMovements, SideColor color, boolean isImportant, int id) {
        this.pieceName = pieceName;
        this.initialSquare = initialSquare;
        this.color = color;
        this.movements = movements;
        this.eatMovements = eatMovements;
        this.isImportant = isImportant;
        this.id = id;
    }

    public Piece(String pieceName, Coordinate initialSquare, List<Move> movements, SideColor color, boolean isImportant, int id) {
        this.pieceName = pieceName;
        this.initialSquare = initialSquare;
        this.color = color;
        this.movements = movements;
        this.eatMovements = movements;
        this.isImportant = isImportant;
        this.id = id;
    }

    public MoveResult<Board, Boolean, SideColor> movePiece(Coordinate initial, Coordinate toSquare, Board board, WinCondition winCondition, LegalMove LegalMove) {
        if (notFollowsCommonRule(toSquare, board)) {
            return new MoveResult<>(board, true,color, "Common Rule unfollowed");
        }
        if (isNotNull(toSquare, board)) {
            return LegalMove.movePiece(this,toSquare, board, initial, eatMovements,winCondition);
        } else {
            return LegalMove.movePiece(this,toSquare, board, initial, movements,winCondition);
        }
    }

    private boolean isNotNull(Coordinate toSquare, Board board) {
        return !Objects.equals(board.getPiece(toSquare).successfulResult().get().getName(), "null");
    }

    private boolean notFollowsCommonRule(Coordinate toSquare, Board board) {
        CommonRule commonRule = new CommonRule();
        return !commonRule.checkRule(board, this, toSquare);
    }

    public String getName() {
        return pieceName;
    }

    public Coordinate getInitialSquare() {
        return initialSquare;
    }

    public Boolean isImportant() {
        return isImportant;
    }

    public SideColor getColor() {
        return color;
    }

    public List<Move> getEatMovements() {
        return eatMovements;
    }

    public List<Move> getMovements() {
        return movements;
    }

    public int getId() {
        return id;
    }

}
