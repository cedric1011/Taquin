import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Board extends JFrame implements ActionListener {
    JButton l1, l2, l3, l4, l5, l6 , l7, l8, l9;
    Border border = LineBorder.createGrayLineBorder();
    Case empty = new Case(0, 0);
    ArrayList<Component> listElement;

    GridLayout grid = new GridLayout(3,3);
    String sauv = "";
    boolean verif = true;

    String[] key = {"1","2","3","4","5","6","7","8"};
    Case findKey = new Case(0, 0);

    private HashMap verifCase;

    Board(ArrayList<Component> listElement, HashMap verifCase) {
        this.listElement = listElement;
        this.verifCase = verifCase;
        setTitle("Taquin");
        setSize(600,600);

        l1 = new JButton("4");
        style(l1);

        l2 = new JButton("3");
        style(l2);

        l3 = new JButton("1");
        style(l3);

        l4 = new JButton("8");
        style(l4);

        l5 = new JButton("9");
        style(l5);

        l6 = new JButton("6");
        style(l6);

        l7 = new JButton("7");
        style(l7);

        l8 = new JButton("5");
        style(l8);

        l9 = new JButton("2");
        style(l9);

        /*1 3 1
        * 8 x 6
        * 7 5 2*/

        this.setLayout(grid);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // a desactiver si on veut jouer solo
        IA();
    }

    //-----------------------------------------------------

    //-----------------------------------------------------IA
    public void IA() {
        for(int i= 1; i <= 3; i++){
            String sauvX = ((String)(verifCase.get(i))).split(":")[0];
            String sauvY = ((String)(verifCase.get(i))).split(":")[1];
            findKey( i+"");
            if(i != 3) {
                while( findKey.getX() != Integer.parseInt(sauvX) || findKey.getY() != Integer.parseInt(sauvY)) {
                    findKey( i+"");
                    findEmpty();
                    System.out.println("empty x : "+ empty.getX() + " y : " + empty.getY());
                    System.out.println("findkey x : "+ findKey.getX() + " y : " + findKey.getY());
                    //les conditions if et else if ne fonctionne que pour la première ligne
                    // de gauche a droite si 1 cote de X
                    if(findKey.getX() > empty.getX() && findKey.getX() - 195 == empty.getX() && findKey.getY() == empty.getY()) {
                        moveIANearby();
                    }
                    //de haute en bas si 1 cote de X
                    else if(findKey.getY() > empty.getY() && findKey.getX() == empty.getX() && findKey.getY() - 187 == empty.getY()) {
                        moveIANearby();
                    }
                    //Autre valeur
                    else {
                        moveIAOtherCase(i);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    findKey(i+"");
                }
            }

        }
        System.out.println(verifCase.toString());
    }

    public void moveIANearby(){
        for(int i = 0; i < listElement.size(); i++){
            if(((JButton)listElement.get(i)).getX() == findKey.getX() &&((JButton)listElement.get(i)).getY() == findKey.getY()) {
                setTextX(i);
            }
        }
        setTextSauv();
    }
    public void moveIAOtherCase(int index){
        if(empty.getY() > findKey.getY() && empty.getX() < findKey.getX()){
            System.out.println("le vide est plus a gauche mais en dessous de la valeur chercher");
            for(int i = 0; i < listElement.size(); i++){
                if(((JButton)listElement.get(i)).getX() == empty.getX() && ((JButton)listElement.get(i)).getY() == empty.getY() - 187) {
                    setTextX(i);
                }
            }
            setTextSauv();
        } else if(empty.getY() == findKey.getY() && empty.getX() > findKey.getX()) {
            System.out.println("sur la meme ligne donc on change de colonne");
            if(empty.getY() == 375 || empty.getY() == 188) {
                for(int i = 0; i < listElement.size(); i++){
                    if(((JButton)listElement.get(i)).getX() == empty.getX() && ((JButton)listElement.get(i)).getY() == empty.getY() - 187) {
                        setTextX(i);
                    }
                }
            }else {
                for(int i = 0; i < listElement.size(); i++){
                    if(((JButton)listElement.get(i)).getX() == empty.getX() && ((JButton)listElement.get(i)).getY() == empty.getY() + 187) {
                        setTextX(i);
                    }
                }
            }
            setTextSauv();
        } else if(empty.getY() > findKey.getY() && empty.getX() >= findKey.getX()) {
            System.out.println("la valeur cherche est sur la mem ligne mais le vide est a droite donc tu passes par en dessous");
            if(index == 1) {
                System.out.println("test");
                for(int i = 0; i < listElement.size(); i++){
                    if(((JButton)listElement.get(i)).getX() == empty.getX() - 195 && ((JButton)listElement.get(i)).getY() == empty.getY()) {
                        setTextX(i);
                    }
                }
            } else {
                if(empty.getX() == 390) {
                    for(int i = 0; i < listElement.size(); i++){
                        if(((JButton)listElement.get(i)).getY() == empty.getY() - 187 && ((JButton)listElement.get(i)).getX() == empty.getX()) {
                            setTextX(i);
                        }
                    }
                } else {
                    for(int i = 0; i < listElement.size(); i++){
                        if(((JButton)listElement.get(i)).getX() == empty.getX() + 195 && ((JButton)listElement.get(i)).getY() == empty.getY()) {
                            setTextX(i);
                        }
                    }
                }

            }
            setTextSauv();
        } else if(empty.getY() < findKey.getY() && empty.getX() < findKey.getX()) {
            System.out.println("la valeur cherche est plus bas et plus a droite que le vide ");
            for(int i = 0; i < listElement.size(); i++){
                if(((JButton)listElement.get(i)).getY() == empty.getY() + 187 && ((JButton)listElement.get(i)).getX() == empty.getX()) {
                    setTextX(i);
                }
            }
            setTextSauv();
        } else if(empty.getY() < findKey.getY() && empty.getX() > findKey.getX()) {
            System.out.println("la valeur cherche est plus bas et plus a gauche que le vide ");
            for(int i = 0; i < listElement.size(); i++){
                if(((JButton)listElement.get(i)).getX() == empty.getX() - 195 && ((JButton)listElement.get(i)).getY() == empty.getY()) {
                    setTextX(i);
                }
            }
            setTextSauv();
        }
    }


    public void setTextSauv() {
        for(int i = 0; i < listElement.size(); i++){
            if(((JButton)listElement.get(i)).getX() == empty.getX() &&
                    ((JButton)listElement.get(i)).getY() == empty.getY()) {
                ((JButton)listElement.get(i)).setText(sauv);
            }
        }
    }
    public void setTextX(int index){
        sauv = ((JButton)listElement.get(index)).getText();
        ((JButton)listElement.get(index)).setText("9");
    }
    public void findEmpty() {
        //find empty
        for(int i = 0; i < listElement.size(); i++){
            //System.out.println(((JButton)listElement.get(i)).getText());
            if(((JButton)listElement.get(i)).getText().equals("9")) {
                empty.setX(((JButton)listElement.get(i)).getX());
                empty.setY(((JButton)listElement.get(i)).getY());
            }
        }
    }

    //permet de trouver l'élément donc d'abord 1, puis 2 etc
    public void findKey(String search){
        for(int i = 0; i < listElement.size(); i++){
            //System.out.println(((JButton)listElement.get(i)).getText());
            if(((JButton)listElement.get(i)).getText().equals(search)) {
                findKey.setX(((JButton)listElement.get(i)).getX());
                findKey.setY(((JButton)listElement.get(i)).getY());
            }
        }

    }

    public boolean finished(){
        String ordre = "";
        for(int i = 0; i < listElement.size() - 1; i++) {
            ordre = ordre + ((JButton)listElement.get(i)).getText();
        }
        for(int i = 0; i < ordre.length() - 1; i++ ){
            System.out.println("ordre "+ ordre);
            if( ordre.charAt(i) > ordre.charAt(i + 1)){
                verif = false;
            } else verif = true;
        }
        if(verif)System.out.println("Bravo");
        return verif;
    }

    public void move(Object source){
        sauv = ((JButton)source).getText();
        ((JButton)source).setText("9");

        for(int i = 0; i < listElement.size(); i++){
            if(((JButton)listElement.get(i)).getX() == empty.getX() &&
                    ((JButton)listElement.get(i)).getY() == empty.getY()) {
                ((JButton)listElement.get(i)).setText(sauv);
            }
        }
    }

    public void style(JButton l) {
        l.setOpaque(true);
        l.setBackground(Color.red);
        l.setHorizontalTextPosition(JLabel.CENTER);
        l.setBorder(border);
        l.setFont(new Font("Serif", Font.ROMAN_BASELINE, 50));
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.addActionListener(this);
        listElement.add(l);
        this.add(l);
    }
    //----------------------------------------------pour jouer solo
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        findEmpty();
        if( ((JButton)source).getY() == empty.getY() &&
                ((JButton)source).getX()  + 195 == (empty.getX())){
            move(source);
        } else if(((JButton)source).getX() - 195 == (empty.getX()) &&
                ((JButton)source).getY() == empty.getY()) {
            move(source);
        } else if( ((JButton)source).getX() == empty.getX() &&
                ((JButton)source).getY() == (empty.getY() + 187)) {
            move(source);
        } else if( ((JButton)source).getX() == empty.getX() &&
                ((JButton)source).getY() == (empty.getY() - 187)) {
            move(source);
        }
        finished();
    }
    //-----------------------------------------------


    public static void main(String[] args) {


        HashMap verifCase = new HashMap();
        verifCase.put(1, "0:1");
        verifCase.put(2, "195:1");
        verifCase.put(3, "390:1");
        verifCase.put(4, "0:188");
        verifCase.put(5, "195:188");
        verifCase.put(6, "390:188");
        verifCase.put(7, "0:395");
        verifCase.put(8, "195:395");


        PriorityQueue file = new PriorityQueue();
        ArrayList<Component> listElement = new ArrayList<>();
        Board frame = new Board(listElement, verifCase);
    }
}
