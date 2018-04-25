package com.ymazza.coursepsi.sudokuym;

/**
 * Created by Pro-MY on 25/04/2018.
 */

public class vGrille {
    int id;
    int lvl;
    int done;

    public vGrille(int id, int lvl){
        setId(id);
        setLvl(lvl);
        setDone(0);
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }

    public void setDone(int done) {
        this.done = (int)(Math.random()*100);
    }

    public int getDone() {
        return done;
    }

}
