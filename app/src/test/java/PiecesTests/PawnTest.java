package PiecesTests;

import chess.logic.classicGame.ChessLegalMove;
import chess.logic.classicGame.ClassicWinCondition;
import chess.logic.moves.HorizontalMove;
import chess.logic.moves.VerticalMove;
import common.models.*;
import common.moves.DiagonalMove;
import common.moves.Move;
import common.results.MoveResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    Game game;

    @BeforeEach
    void setup(){
        List<PieceCoord> blackPieces = new ArrayList<>();
        List<PieceCoord> whitePieces = new ArrayList<>();
        PieceFactory pieceFactory = new PieceFactory();
        List<Move> pawnMovements = new ArrayList<>();
        pawnMovements.add(new VerticalMove(1, false));
        pawnMovements.add(new VerticalMove(2, false));
        whitePieces.add(new PieceCoord(new Coordinate(1, 2),pieceFactory.createPiece("pawn", pawnMovements, false, SideColor.White)));
        whitePieces.add(new PieceCoord(new Coordinate(5, 1),pieceFactory.clonePiece("pawn", SideColor.White)));
        blackPieces.add(new PieceCoord(new Coordinate(6, 2),pieceFactory.clonePiece("pawn", SideColor.Black)));
        blackPieces.add(new PieceCoord(new Coordinate(1, 7),pieceFactory.clonePiece("pawn", SideColor.Black)));

        List<Move> kingMovements = new ArrayList<>();
        kingMovements.add(new VerticalMove(1, true));
        kingMovements.add(new HorizontalMove(1));
        kingMovements.add(new DiagonalMove(1, 1,true));
        kingMovements.add(new DiagonalMove(1, -1,true));
        whitePieces.add(new PieceCoord(new Coordinate(5, 1),pieceFactory.createPiece("king", kingMovements, true, SideColor.White)));
        blackPieces.add(new PieceCoord(new Coordinate(5, 8),pieceFactory.clonePiece("king", SideColor.Black)));


        Board board = new Board(8,8,blackPieces,whitePieces,pieceFactory);
        game = new Game(board, SideColor.White,new ClassicWinCondition(), new ChessLegalMove());
    }
    @Test
    void TestIfPawnCanMoveDiagonalWithoutEating() {
        MoveResult<Board, Boolean,SideColor> g = game.movePiece(new Coordinate(1, 2), new Coordinate(2, 3));
        assertEquals("Piece not moved", g.message());
    }
    @Test
    void TestIfPawnCanMoveDiagonalWithEating() {
        MoveResult<Board, Boolean,SideColor> g = game.movePiece(new Coordinate(5, 1), new Coordinate(6, 2));
        assertEquals("Piece Moved", g.message());
    }
    @Test
    void testValidForwardMove() {
        MoveResult<Board, Boolean,SideColor> result = game.movePiece(new Coordinate(1, 2), new Coordinate(1, 3));
        assertEquals("Piece Moved", result.message());
    }

    @Test
    void testInvalidBackwardMove() {
        MoveResult<Board, Boolean,SideColor> result = game.movePiece(new Coordinate(1, 2), new Coordinate(1, 1));
        assertEquals("Piece not moved", result.message());
    }
}
