package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MainBoardPanel extends JPanel implements MouseListener   {
    private Polygon viewB1, viewB2, viewPage, downMove, upMove, leftMove, rightMove;
    private boolean viewVis = false;
    private BufferedImage backgroundImage, bearScoring, hawkScoring, salmonScoring, elkScoring, foxScoring, natureToken;
    private BufferedImage dd, dl, ds, fd, ff, fl, fs, ll, lm, md, mf, mm, ms, sl, ss;
    private BufferedImage bear, elk, fox, hawk, salmon;
    private boolean isVisible = true;
    private int offsetx, offsety = 0;

    public MainBoardPanel() {

        addMouseListener(this);
        importImages();
    }

    public void paint(Graphics g, int width, int height) {


        int div = 5;

        int boardCenterx = ((div-1)/2) * (width/div);
        int boardCentery = ((div-1)/2) * (height/div);

        g.drawImage(backgroundImage, 0, 0, null);
        Color beigeColor = new Color(255, 221, 122);
        g.setColor(beigeColor);
        g.fillRoundRect(width/100, height/100, width/8, height/14, 30, 30);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, width/90));
        g.drawString("Turn: 1", width/19, height/19);

        BufferedImage[] buffList = {ss, bear};
        BufferedImage test = CascadiaPanel.drawTiles(buffList);
        g.drawImage(test, boardCenterx+offsetx, boardCentery+offsety, null);


        drawScoring(g, width, height, div);
        //Draw the Player 2 and Player 3 buttons
        g.fillRoundRect(width/2 + width/6, height/100, width/8, height/14, 10, 10);
        g.fillRoundRect(width/2 + width/6, height/100 + height/14 + height/100, width/8, height/14, 10, 10);
        g.setColor(beigeColor);
        g.fillRect(width-width/div, 0, width/div, height);
        //Draw a large rectangle covering the bottom of the screen
        g.fillRect(0, height-height/div, width, height/div);
        g.fillRoundRect(width/2 + width/6, height/100, width/8, height/14, 30, 30);

        g.fillRoundRect(width/2 + width/6, height/100 + height/14 + height/100, width/8, height/14, 30, 30);

        g.setColor(Color.BLACK);
        int text = width/2 + width/6 + width/23;

        g.drawString("Player 2", text, height/19); // Change text to change with turn
        g.drawString("Player 3", text, height/19 + height/14 + height/100);

        int debugRectWidth = width/8;
        int debugRectHeight = height/14;
        int debugXPos = width/2 + width/6;
        int debugYPos = height/100 + height/14 + height/100;

        int[] xPoints = {debugXPos, debugXPos, debugXPos+debugRectWidth, debugXPos+debugRectWidth};
        int[] yPoints = {debugYPos+debugRectHeight, debugYPos, debugYPos, debugYPos+debugRectHeight};

        viewB1 = new Polygon(xPoints, yPoints, 4);

        //g.drawPolygon(viewB1);

        int debugRectWidth2 = width/8;
        int debugRectHeight2 = height/14;
        int debugXPos2 = width/2 + width/6;
        int debugYPos2 = height/100;

        int[] xPoints2 = {debugXPos2, debugXPos2, debugXPos2+debugRectWidth2, debugXPos2+debugRectWidth2};
        int[] yPoints2 = {debugYPos2+debugRectHeight2, debugYPos2, debugYPos2, debugYPos2+debugRectHeight2};

        viewB2 = new Polygon(xPoints2, yPoints2, 4);

        int playAreaWidth = (width - width/div);
        int playAreaHeight = (height - height/div);

        int debugRectWidth3 = playAreaWidth/2 + playAreaWidth/3;
        int debugRectHeight3 = playAreaHeight/2 + playAreaHeight/3;
        int debugXPos3 = (playAreaWidth/2 + playAreaWidth/3)/10;
        int debugYPos3 = (playAreaHeight/2 + playAreaHeight/3)/10;

        int[] xPoints3 = {debugXPos3, debugXPos3, debugXPos3+debugRectWidth3, debugXPos3+debugRectWidth3};
        int[] yPoints3 = {debugYPos3+debugRectHeight3, debugYPos3, debugYPos3, debugYPos3+debugRectHeight3};

        viewPage = new Polygon(xPoints3, yPoints3, 4);

        if (viewVis) {
            g.setColor(beigeColor);
            g.fillPolygon(viewPage);
        }
        //Draw a vertical line 150 pixels dividing the bottom rectangle into a square and long rectangle
        g.drawLine(width-width/div, 0, width-width/div, height);
        g.drawLine(width/div - 75, height-height/div, width/div - 75, height);
        //Draw 4 nature tokens in each corner of the square in the bottom left corner
        g.drawImage(natureToken, 0, height - height/div, null);
        g.drawImage(natureToken, 0, height - 50, null);
        g.drawImage(natureToken, width/div - 140, height - 50, null);
        g.drawImage(natureToken, width/div - 140, height - height/div, null);

        //Draw the player's nature tokens in the bottom right corner in the center
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("0", width/div - 235, height - height/div + height/div/2 + 5);

        int debugRectWidth4 = playAreaWidth;
        int debugRectHeight4 = playAreaHeight/4;
        int debugXPos4 = 0;
        int debugYPos4 = 0;

        int[] xPoints4 = {debugXPos4, debugXPos4, debugXPos4+debugRectWidth4, debugXPos4+debugRectWidth4};
        int[] yPoints4 = {debugYPos4+debugRectHeight4, debugYPos4, debugYPos4, debugYPos4+debugRectHeight4};

        debugRectWidth4 = playAreaWidth;
        debugRectHeight4 = playAreaHeight/4;
        debugXPos4 = 0;
        debugYPos4 = playAreaHeight-playAreaHeight/4;

        int[] xPoints5 = {debugXPos4, debugXPos4, debugXPos4+debugRectWidth4, debugXPos4+debugRectWidth4};
        int[] yPoints5 = {debugYPos4+debugRectHeight4, debugYPos4, debugYPos4, debugYPos4+debugRectHeight4};

        debugRectWidth4 = playAreaWidth/4;
        debugRectHeight4 = playAreaHeight;
        debugXPos4 = 0;
        debugYPos4 = 0;

        int[] xPoints6 = {debugXPos4, debugXPos4, debugXPos4+debugRectWidth4, debugXPos4+debugRectWidth4};
        int[] yPoints6 = {debugYPos4+debugRectHeight4, debugYPos4, debugYPos4, debugYPos4+debugRectHeight4};

        debugRectWidth4 = playAreaWidth/4;
        debugRectHeight4 = playAreaHeight;
        debugXPos4 = playAreaWidth-playAreaWidth/4;
        debugYPos4 = 0;

        int[] xPoints7 = {debugXPos4, debugXPos4, debugXPos4+debugRectWidth4, debugXPos4+debugRectWidth4};
        int[] yPoints7 = {debugYPos4+debugRectHeight4, debugYPos4, debugYPos4, debugYPos4+debugRectHeight4};

        upMove = new Polygon(xPoints4, yPoints4, 4);
        downMove = new Polygon(xPoints5, yPoints5, 4);
        leftMove = new Polygon(xPoints6, yPoints6, 4);
        rightMove = new Polygon(xPoints7, yPoints7, 4);

        g.drawPolygon(upMove);
        g.drawPolygon(downMove);
        g.drawPolygon(leftMove);
        g.drawPolygon(rightMove);


    }
    /**
     * Imports the images that are used in the game. The images are stored in the resources folder, and are imported using the ImageIO class.
     */
    private void importImages(){
        try{
            backgroundImage = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Menu/Background.jpg")));
            bearScoring = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/bear-small.jpg")));
            hawkScoring = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/hawk-small.jpg")));
            salmonScoring = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/salmon-small.jpg")));
            elkScoring = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/elk-small.jpg")));
            foxScoring = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/fox-small.jpg")));
            natureToken = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/nature-token.png")));
            dd = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/dd.png")));
            dl = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/dl.png")));
            ds = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/ds.png")));
            fd = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/fd.png")));
            ff = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/ff.png")));
            fl = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/fl.png")));
            fs = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/fs.png")));
            ll = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/ll.png")));
            lm = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/lm.png")));
            md = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/md.png")));
            mf = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/mf.png")));
            mm = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/mm.png")));
            ms = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/ms.png")));
            sl = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/sl.png")));
            ss = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/Tiles/ss.png")));
            bear = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/BEAR.png")));
            elk = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/BEAR.png")));
            fox = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/BEAR.png")));
            hawk = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/BEAR.png")));
            salmon = ImageIO.read(Objects.requireNonNull(CascadiaPanel.class.getResource("/Images/WildlifeTokens/BEAR.png")));


        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        bearScoring = CascadiaPanel.resizeImage(bearScoring, 160, 160);
        hawkScoring = CascadiaPanel.resizeImage(hawkScoring, 160, 160);
        salmonScoring = CascadiaPanel.resizeImage(salmonScoring, 160, 160);
        elkScoring = CascadiaPanel.resizeImage(elkScoring, 160, 160);
        foxScoring = CascadiaPanel.resizeImage(foxScoring, 160, 160);
    }

    /**
     * Draws the scoring on the right side of the screen. The scoring is drawn in the order of bear, hawk, salmon, elk, and fox.
     * @param g The <code>Graphics</code> object that is used to draw the scoring.
     * @param width The width of the screen.
     * @param height The height of the screen.
     * @param div The number of divisions that the screen is divided into.
     */
    private void drawScoring(Graphics g, int width, int height, int div){
        g.drawRect(width-width/div, 0, width/div, height);
        int x = width - width/div + width/div/2 - bearScoring.getWidth()/2;
        int y = height/div/2 - bearScoring.getHeight()/2;
        g.drawImage(bearScoring, x, y, null);
        y += height/div;
        g.drawImage(hawkScoring, x, y, null);
        y += height/div;
        g.drawImage(salmonScoring, x, y, null);
        y += height/div;
        g.drawImage(elkScoring, x, y, null);
        y += height/div;
        g.drawImage(foxScoring, x, y, null);
    }


    public void setVisible(boolean val) {
        isVisible = val;
    }
    public boolean getVisible() {
        return isVisible;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (viewB1.contains(x, y)) {
            viewVis = true;
        } else if (viewB2.contains(x, y)) {
            viewVis = true;
        } else if (viewPage.contains(x,y)) {
            viewVis = false;
        }

        if (upMove.contains(x, y)) {
            offsety = offsety-10;
        }
        if (downMove.contains(x, y)) {
            offsety = offsety+10;
        }
        if (leftMove.contains(x, y)) {
            offsetx = offsetx-10;
        }
        if (rightMove.contains(x, y)) {
            offsetx = offsetx+10;
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
