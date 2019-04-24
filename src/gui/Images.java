package gui;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class Images{
    public static String BALL = "rsc/Ballon.png";
    public static String CARD_FORM_1 = "rsc/carte1.png";
    public static String CARD_FORM_2 = "rsc/carte2.png";
    public static String CARD_FORM_3 = "rsc/carte3.png";
    public static String CARD_FORM_4 = "rsc/carte4.png";
    public static String CARD_FORM_5 = "rsc/carte5.png";
    public static String CARD_FORM_6 = "rsc/carte6.png";
    public static String PLAYER_BLUE_HARD = "rsc/durBleu.png";
    public static String PLAYER_BLUE_SMART = "rsc/futéBleu.png";
    public static String PLAYER_BLUE_FAT = "rsc/grosBleu.png";
    public static String PLAYER_BLUE_NORMAL = "rsc/ordinaireBleu.png";
    public static String PLAYER_BLUE_FAST = "rsc/rapideBleu.png";
    public static String PLAYER_BLUE_TACKL = "rsc/retourneBleu.png";
    public static String PLAYER_RED_HARD = "rsc/durRouge.png";
    public static String PLAYER_RED_SMART = "rsc/futéRouge.png";
    public static String PLAYER_RED_FAT = "rsc/grosRouge.png";
    public static String PLAYER_RED_NORMAL = "rsc/ordinaireRouge.png";
    public static String PLAYER_RED_FAST = "rsc/rapideRouge.png";
    public static String PLAYER_RED_TACKL = "rsc/retourneRouge.png";
    public static String TERRAIN = "rsc/terrain.png";
    public static String BUTTON_ACTIONS_1 = "rsc/BarreAvecBallon.png";
    public static String BUTTON_ACTIONS_2 = "rsc/BarreSansBallon.png";
    public static String BUTTON_CARDS = "rsc/LogoCartes.png";
    public static String BUTTON_NEXT = "rsc/NextRound.png";

    private static HashMap<String, BufferedImage> images;

    public static void load(){
        images = new HashMap<String, BufferedImage>();
        try{
            images.put(Images.BALL, ImageIO.read(new File(Images.BALL)));
            images.put(Images.CARD_FORM_1, ImageIO.read(new File(Images.CARD_FORM_1)));
            images.put(Images.CARD_FORM_2, ImageIO.read(new File(Images.CARD_FORM_2)));
            images.put(Images.CARD_FORM_3, ImageIO.read(new File(Images.CARD_FORM_3)));
            images.put(Images.CARD_FORM_4, ImageIO.read(new File(Images.CARD_FORM_4)));
            images.put(Images.CARD_FORM_5, ImageIO.read(new File(Images.CARD_FORM_5)));
            images.put(Images.CARD_FORM_6, ImageIO.read(new File(Images.CARD_FORM_6)));
            images.put(Images.PLAYER_BLUE_HARD, ImageIO.read(new File(Images.PLAYER_BLUE_HARD)));
            images.put(Images.PLAYER_BLUE_SMART, ImageIO.read(new File(Images.PLAYER_BLUE_SMART)));
            images.put(Images.PLAYER_BLUE_FAT, ImageIO.read(new File(Images.PLAYER_BLUE_FAT)));
            images.put(Images.PLAYER_BLUE_NORMAL, ImageIO.read(new File(Images.PLAYER_BLUE_NORMAL)));
            images.put(Images.PLAYER_BLUE_FAST, ImageIO.read(new File(Images.PLAYER_BLUE_FAST)));
            images.put(Images.PLAYER_BLUE_TACKL, ImageIO.read(new File(Images.PLAYER_BLUE_TACKL)));
            images.put(Images.PLAYER_RED_HARD, ImageIO.read(new File(Images.PLAYER_RED_HARD)));
            images.put(Images.PLAYER_RED_SMART, ImageIO.read(new File(Images.PLAYER_RED_SMART)));
            images.put(Images.PLAYER_RED_FAT, ImageIO.read(new File(Images.PLAYER_RED_FAT)));
            images.put(Images.PLAYER_RED_NORMAL, ImageIO.read(new File(Images.PLAYER_RED_NORMAL)));
            images.put(Images.PLAYER_RED_FAST, ImageIO.read(new File(Images.PLAYER_RED_FAST)));
            images.put(Images.PLAYER_RED_TACKL, ImageIO.read(new File(Images.PLAYER_RED_TACKL)));
            images.put(Images.TERRAIN, ImageIO.read(new File(Images.TERRAIN)));
            images.put(Images.BUTTON_ACTIONS_1, ImageIO.read(new File(Images.BUTTON_ACTIONS_1)));
            images.put(Images.BUTTON_ACTIONS_2, ImageIO.read(new File(Images.BUTTON_ACTIONS_2)));
            images.put(Images.BUTTON_CARDS, ImageIO.read(new File(Images.BUTTON_CARDS)));
            images.put(Images.BUTTON_NEXT, ImageIO.read(new File(Images.BUTTON_NEXT)));
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static BufferedImage get(String id){
        if(images != null){
            return images.get(id);
        } else{
            return null;
        }
    }
}