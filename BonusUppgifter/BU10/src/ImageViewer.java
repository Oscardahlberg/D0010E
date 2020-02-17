import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 *
 * Coded by Oscar Dahlberg for course D0010E at LTU for BU10
 *
 */

public class ImageViewer extends JFrame{

    private JFrame frame;
    private JPanel panel;

    private JLabel imageLabel;
    private JButton button;
    private URL url;
    private Icon image;

    //Håller reda på vilken bild som ska visas
    private int urlIndex = 0;
    private String urlTeater = "http://www.sm.luth.se/csee/courses/d0010e/l/prob/10tj5Ei9o/LTU-Teatern.jpg";
    private String urlVetenskap = "http://www.sm.luth.se/csee/courses/d0010e/l/prob/10tj5Ei9o/LTU-Vetenskapens-hus.jpg";

    ImageViewer(){

        frame = new JFrame("ImageViewer");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        button = new JButton("Change");

        try {
            //Lägger till första bilden "Teater" från en url
            this.url = new URL(urlTeater);
            this.image = new ImageIcon(ImageIO.read(url));
            this.imageLabel = new JLabel(image);

            panel.add(imageLabel);
            panel.add(button);

            //Sätter ut knappen och bilden så att de automatiskt får plats bredvid varann
            frame.setLayout(new FlowLayout(FlowLayout.LEFT));
            frame.pack();

            frame.setSize(285, 170);
            frame.add(panel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.button.addActionListener(new ActionListener() {

            //En anonym klass som blir kallad varje gång knappen "button" blir tryckt
            public void actionPerformed(ActionEvent e) {
                try {

                    //Om urlIndex är 0 visas bilden av vetenskap huset
                    //Om urlIndex är 1 visas bilden av teatern
                    if(urlIndex == 0){
                        url = new URL(urlVetenskap);
                        urlIndex++;
                    } else{
                        url = new URL(urlTeater);
                        urlIndex--;
                    }

                    //Sätter så att variabeln imageLabel blir den nya bilden
                    image = new ImageIcon(ImageIO.read(url));
                    imageLabel.setIcon(image);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

}
