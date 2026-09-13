package utils;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {

    private final Color color;
    private final int thickness;
    private final int radius;

    public RoundedBorder(Color color, int thickness, int radius) {
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
    }

    @Override
    public void paintBorder(
            Component c,
            Graphics g,
            int x,
            int y,
            int width,
            int height
    ) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));

        g2.drawRoundRect(
                x + thickness / 2,
                y + thickness / 2,
                width - thickness,
                height - thickness,
                radius,
                radius
        );

        g2.dispose();
    }
}