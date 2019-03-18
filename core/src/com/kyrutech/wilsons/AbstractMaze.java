package com.kyrutech.wilsons;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class AbstractMaze {

    List<Cell> maze = new ArrayList<>();
    Stack<Cell> path = new Stack<>();

    public abstract void next();
    public abstract boolean isComplete();
}
