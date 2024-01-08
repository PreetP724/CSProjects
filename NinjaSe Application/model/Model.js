import { toHaveFocus } from "@testing-library/jest-dom/matchers";

// how are we figuring out winner and where to put it
export class MoveType {
    constructor(dr, dc) {
        this.deltar = dr;
        this.deltac = dc;
    }
    
    static parse(s) {
        if ((s === "down")  || (s === "Down"))   { return Down; }
        if ((s === "up")    || (s === "Up"))     { return Up; }
        if ((s === "left")  || (s === "Left"))   { return Left; }
        if ((s === "right") || (s === "Right"))  { return Right; }
        
        return NoMove;
    }
}
    
export const Down = new MoveType(1, 0, "down");
export const Up = new MoveType(-1, 0, "up");
export const Left = new MoveType(0, -1, "left");
export const Right = new MoveType(0, 1, "right");
export const NoMove = new MoveType(0, 0, "*");  


export class Square {
    constructor(row, col, color){
        this.row = row;
        this.col = col;
        this.color = color;
    }

    canEvenSquareMove(direction, boardrows, boardcolumns){
        if(((this.row + direction.deltar) !== boardrows) && ((this.col + direction.deltac) !== boardcolumns) &&  ((this.row + direction.deltar) !== -1) && ((this.col + direction.deltac) !== -1)) {
            return true;
        }
        return false;
    }

    changeSquareCoord(direction){
        this.row += direction.deltar;
       this.col += direction.deltac;
    }


    

}

export class Ninja{
    constructor(row, column){
        this.row = row;
        this.column = column;
    }

    moveNinja(direction){
        
        this.row += direction.deltar;
        this.column += direction.deltac;
        
        
    }

    columnsOccupied(direction){
        if(direction === Left){
            return this.column;
        }
        else{
            return this.column - 1;
        }
        
    }

    rowsOccupied(direction){
        //console.log(direction);
        if(direction === Up){
            console.log("hi");
            return this.row;
        }
        else{
            return this.row - 1;
        }
    }


}

export class Board{
    constructor(numRows, numColumns, numColors){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numColors = numColors;
    }


    initialize(squares, ninja){
        this.squares = squares;
        this.ninja = ninja;
    }

    
    hasSquare(row, col){
        if(this.squares[row][col] !== 0){
            return true;
        }
        return false;
    }

    getSquareAt(row, col){
       return this.squares[row][col];
    }

    canEvenNinjaMove(direction){
        if(((this.ninja.row + direction.deltar) !== this.numRows -1) && ((this.ninja.column + direction.deltac) !== this.numColumns - 1) &&  ((this.ninja.row + direction.deltar) !== -1) && ((this.ninja.column + direction.deltac) !== -1)) {
            return true;
        }
        return false;
    }

    moveNinja(direction){
            this.ninja.row += direction.deltar;
            this.ninja.column += direction.deltac;
    }

    hasGroup(){
       
        let arr = [];
        let exists = false;
        for(let i = 0; i < this.squares.length -1; i++){
            for(let k = 0; k < this.squares[i].length - 1; k++){
                console.log("here");
                if((this.squares[i][k] !== 0) && (this.squares[i][k+1] !== 0) && (this.squares[i+1][k] !== 0) && (this.squares[i+1][k+1] !== 0)){
                    console.log("inside");
                    console.log(this.squares[i][k].color === this.squares[i][k+1].color);
                if((this.squares[i][k].color === this.squares[i][k+1].color) && (this.squares[i][k+1].color === this.squares[i+1][k+1].color) && (this.squares[i][k].color === this.squares[i+1][k].color) ){
                    
                    arr.push(i);
                    arr.push(k);

                    exists = true;
                    break;
                }
            }
            }
            if(exists){
                break;
            }
        }
        return arr;
    }

    hasWon(){
        if(this.numColors === 0){
            return true;
        }
        return false;
    }
    /*
    allSquaresCovered(ninjaPlace){

    }


    moveAdjacentSquares(direction){
        if(this.hasSquare(this.ninja.row, this.ninja.column)){
            
           let square = this.getSquareAt(this.ninja.row, this.ninja.column);
           this.squares[square.row][square.col] = 0;
           // this.squares[square.row][square.col] = null;
           // this.moveSquareBoard(square, direction);
            square.changeSquareCoord(direction);
            this.squares[square.row][square.col] = square;
            //this.squares[this.ninja.row, this.ninja.column] = [ninja.col]
            
        }
        
    }
    */
    


}

export class Model {
    constructor(info){
        this.initialize(info);
        this.info = info;
    }

    initialize(info){

        let numRows = parseInt(info.numRows);
        let numColumns = parseInt(info.numColumns);
        let numSquares = info.initial.length; 
        console.log(numSquares);
         var allSquares = [];
        // console.log(allSquares.length);
         allSquares.length = numRows;

         for (let r = 0; r < numRows; r++) { 
            var arrIn = [];
            arrIn.length = numColumns;
            allSquares[r] = arrIn;
        }
        // console.log(allSquares);
        for(let r = 0; r < allSquares.length; r++){
            for(let c = 0; c < allSquares[r].length; c++){
                for(let square of info.initial){
                    let value = square.column.charCodeAt(0);
                
                   //console.log(allSquares[r][c]);
                    if(square.row -1 === r && (value - 65 === c)){
                        allSquares[r][c] = new Square(square.row -1, value - 65, square.color);
                        //console.log(allSquares[r][c]);
                    }
                    else if(typeof allSquares[r][c] === 'undefined'){
                        allSquares[r][c] = 0;
                    } 
                    
            }
        }
        }

        //allSquares[1][3] = new Square(1, 3, "green");
        this.board = new Board(numRows, numColumns,  numSquares);
        this.board.initialize(allSquares, new Ninja(parseInt(info.ninjaRow) - 1, info.ninjaColumn.charCodeAt(0) - 65));
        
        this.moveCount = 0;
        this.scoreCount = 0;
        this.hasWon = false;

    }

    isVictorious(){
        this.hasWon = this.board.hasWon();
    }
    



}



//When moving check if there is a square affected first