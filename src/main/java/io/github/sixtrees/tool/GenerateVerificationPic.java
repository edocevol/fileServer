package io.github.sixtrees.tool;

import com.sun.jimi.core.Jimi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

@Entity
public class GenerateVerificationPic {
    @Id
    @GeneratedValue
    private int WIDTH = 100;
    private int HEIGHT = 40;

    private static final int NUM = 4;
    private static final int DISTURB_LINE_NUM = 6;

    private static final char[] seq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};

    public GenerateVerificationPic(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public GenerateVerificationPic() {
    }

    public void setSize(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public byte[] generateVerificationPic(StringBuffer code) {
        Random r = new Random();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        Graphics g = image.getGraphics();
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(new Color(0, 0, 0));

        //绘制验证码图片
        for (int i = 0; i < NUM; i++) {
            g.setColor(randomColor(r));
            int h = (int) ((HEIGHT * 40 / 100) * r.nextDouble() + (HEIGHT * 60 / 100));
            g.setFont(new Font(null, Font.BOLD | Font.ITALIC, h));
            g.drawString(code.substring(i, i + 1), i * WIDTH / NUM * 90 / 100, h);
        }

        //绘制干扰射线
        for (int i = 0; i <= DISTURB_LINE_NUM; i++) {
            g.setColor(randomColor(r));
            g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r.nextInt(HEIGHT));
        }

        try {
            Jimi.putImage("image/jpeg", image, os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    public StringBuffer generateVerificationCode() {
        Random r = new Random();
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < NUM; i++) {
            String ch = String.valueOf(seq[r.nextInt(seq.length)]);
            code.append(ch);
        }
        System.out.println("验证码为：" + code.toString());
        return code;
    }

    private Color randomColor(Random r) {
        return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
}
