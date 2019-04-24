package engine.model;

import java.awt.image.*;
import java.util.*;

/**
 * Player information
 */
public class PlayerState{

    public String image;
    public String tacklImage;
    public int moves;
    public int attack;
    public int defense;

    public PlayerState(String image, String tacklImage, int moves, int attack, int defense){
        this.image = image;
        this.tacklImage = tacklImage;
        this.moves = moves;
        this.attack = attack;
        this.defense = defense;
    }
}