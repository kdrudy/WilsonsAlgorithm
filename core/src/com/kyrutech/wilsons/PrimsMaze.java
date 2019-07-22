package com.kyrutech.wilsons;

import java.util.*;

public class PrimsMaze extends AbstractMaze {

    int width, height;

    Cell currentCell;

    public PrimsMaze(int width, int height) {
        this.width = width;
        this.height = height;

        int initialX = (int) (Math.random()*width);
        int initialY = (int) (Math.random()*height);
        currentCell = new Cell(initialX, initialY);
        maze.add(currentCell);
    }

    @Override
    public void next() {
        if(!isComplete()) {
            //Add cells adjacent to current to marked
            addNewMarkedCells();

            //Select a marked cell at random
            Collections.shuffle(path);
            currentCell = path.remove(0);

            //Find visited cells adjacent to new currentCell
            List<Cell> adjacent = getAdjacentVisitedCells();

            //Select adjacent cell at random
            Collections.shuffle(adjacent);
            Cell adjacentCell = adjacent.remove(0);

            //Create hallway between cells
            if(adjacentCell.x == currentCell.x) {
                if(adjacentCell.y < currentCell.y) {
                    adjacentCell.south = true;
                    currentCell.north = true;
                } else {
                    adjacentCell.north = true;
                    currentCell.south = true;
                }
            } else {
                if(adjacentCell.x < currentCell.x) {
                    adjacentCell.east = true;
                    currentCell.west = true;
                } else {
                    adjacentCell.west = true;
                    currentCell.east = true;
                }
            }
            maze.add(currentCell);
        }
    }

    private List<Cell> getAdjacentVisitedCells() {
        List<Cell> adjacent = new ArrayList<>();
        if(currentCell.x > 0) {
            Cell newCell = new Cell(currentCell.x-1, currentCell.y);
            if(maze.contains(newCell)) {
                adjacent.add(maze.get(maze.indexOf(newCell)));
            }
        }
        if(currentCell.x < width-1 ) {
            Cell newCell = new Cell(currentCell.x+1, currentCell.y);
            if(maze.contains(newCell)) {
                adjacent.add(maze.get(maze.indexOf(newCell)));
            }
        }
        if(currentCell.y > 0) {
            Cell newCell = new Cell(currentCell.x, currentCell.y-1);
            if(maze.contains(newCell)) {
                adjacent.add(maze.get(maze.indexOf(newCell)));
            }
        }
        if(currentCell.y < height-1) {
            Cell newCell = new Cell(currentCell.x, currentCell.y+1);
            if(maze.contains(newCell)) {
                adjacent.add(maze.get(maze.indexOf(newCell)));
            }
        }
        return adjacent;
    }

    private void addNewMarkedCells() {
        if(currentCell.x > 0) {
            Cell newCell = new Cell(currentCell.x-1, currentCell.y);
            if(!path.contains(newCell) && !maze.contains(newCell)) {
                path.add(newCell);
            }
        }
        if(currentCell.x < width-1 ) {
            Cell newCell = new Cell(currentCell.x+1, currentCell.y);
            if(!path.contains(newCell) && !maze.contains(newCell)) {
                path.add(newCell);
            }
        }
        if(currentCell.y > 0) {
            Cell newCell = new Cell(currentCell.x, currentCell.y-1);
            if(!path.contains(newCell) && !maze.contains(newCell)) {
                path.add(newCell);
            }
        }
        if(currentCell.y < height-1) {
            Cell newCell = new Cell(currentCell.x, currentCell.y+1);
            if(!path.contains(newCell) && !maze.contains(newCell)) {
                path.add(newCell);
            }
        }
    }

    @Override
    public boolean isComplete() {
        return maze.size() == width*height;
    }
}
