import React from 'react';
import './App.css';
import { layout } from './Layout.js';
import { Model } from './model/Model';
import { config_5x5 } from './model/Board';
import {config_6x6 } from './model/Board';
import {config_4x4} from './model/Board';
import { redrawBoard } from './boundary/Boundary';
import { moveNinjaHorizontal, moveNinjaVertical } from './controller/Controller';
import {Up, Down, Left, Right} from './model/Model.js';
import { removeColorGroup } from './controller/Controller';
import ninjase from './ninja-se.svg';


var boardInfo = JSON.parse(JSON.stringify(config_5x5));
var thirdinfo = JSON.parse(JSON.stringify(config_6x6));
var secondinfo = JSON.parse(JSON.stringify(config_4x4));

function App() {
  

  const [model, setModel] = React.useState(new Model(boardInfo));
  const [redraw, forceRedraw] = React.useState(0);
  
  const canvasRef = React.useRef(null);

  React.useEffect (() => {
    redrawBoard(model, canvasRef.current);
  }, [model, redraw] )


const moveNinjaHandler = (direction) => {
  moveNinjaVertical(model, direction);
  forceRedraw(redraw+1);
}

const moveNinjaHandlerHorizontal = (direction) => {
  moveNinjaHorizontal(model, direction);
  forceRedraw(redraw+1);
}

const removeGroupHandler = (model) => {
  removeColorGroup(model);
  forceRedraw(redraw+1);
}

const changeConfigHandler1 = (model) => {
  setModel(model = new Model(boardInfo))
  forceRedraw(redraw+1);
}

const changeConfigHandler2 = (model) => {
  setModel(model = new Model(secondinfo))
  forceRedraw(redraw+1);
}

const changeConfigHandler3 = (model) => {
  setModel(model = new Model(thirdinfo))
  forceRedraw(redraw+1);
}

const resetPuzzleHandler = (model) => {
  setModel(model = new Model(model.info))
  forceRedraw(redraw+1);
}

  
  return (

    <main style = {layout.Appmain} >
      <canvas style = {layout.canvas } width = {layout.canvas.width} height = {layout.canvas.height} ref = {canvasRef}> </canvas>

    <label style={layout.movetext}> {"Move Count: " + model.moveCount} </label>
    <label style = {layout.scoretext}> {"Score: " + model.scoreCount} </label>
    
    <button style={layout.upbutton}  onClick = {(e) => moveNinjaHandler(Up)} disabled = {!model.board.canEvenNinjaMove(Up) || model.hasWon}> ^ </button>
    <button style={layout.downbutton}  onClick = {(e) => moveNinjaHandler(Down)} disabled = {!model.board.canEvenNinjaMove(Down) || model.hasWon}> v </button>
    <button style={layout.rightbutton}  onClick = {(e) => moveNinjaHandlerHorizontal(Right)} disabled = {!model.board.canEvenNinjaMove(Right) || model.hasWon}> &gt; </button>
    <button style={layout.leftbutton} onClick = {(e) => moveNinjaHandlerHorizontal(Left)}  disabled = {!model.board.canEvenNinjaMove(Left) || model.hasWon}> &lt; </button>
    <button style = {layout.removeGroupButton}  onClick = {(e) => removeGroupHandler(model)} disabled = {model.hasWon}> Remove Group</button>
    <button style = {layout.resetbutton} onClick = {(e) => resetPuzzleHandler(model)}> Reset Game</button>
    <button style = {layout.level1button} onClick = {(e) => changeConfigHandler1(model)} > Level 1</button>
    <button style = {layout.level2button}  onClick = {(e) => changeConfigHandler2(model)}> Level 2</button>
    <button style = {layout.level3button} onClick = {(e) => changeConfigHandler3(model)}> Level 3</button>
   
    { model.hasWon ? (<label data-testid="victory-label" style={layout.victory}>You Won! Choose another level or reset the game to play.</label>) : null }
    
    <img id="ninjase" src={ninjase} alt="hidden" hidden></img>

    </main>
   
   
  );
}

export default App;
