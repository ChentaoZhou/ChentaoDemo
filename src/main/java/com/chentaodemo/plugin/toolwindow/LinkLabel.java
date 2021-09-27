package com.chentaodemo.plugin.toolwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class LinkLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    private String text;

    private URL link = null;

    private Color preColor = null;

    /**
     * create text with hyperlink
     * @param vText
     * @param vLink
     */
    public LinkLabel(String vText, String vLink) {
        super("<html>" + vText + "</html>");
        this.text = vText;
        try {
            this.link = new URL(vLink);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                LinkLabel.this.setCursor(Cursor
                        .getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                if (preColor != null)
                    LinkLabel.this.setForeground(preColor);
                LinkLabel.this.setText("<html>" + text + "</html>");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                LinkLabel.this.setCursor(Cursor
                        .getPredefinedCursor(Cursor.HAND_CURSOR));
                preColor = LinkLabel.this.getForeground();
                LinkLabel.this.setForeground(Color.BLUE);
                LinkLabel.this.setText("<html><u>" + text + "</u></html>");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(link.toURI());
                } catch (IOException err) {
                    err.printStackTrace();
                } catch (URISyntaxException err) {
                    err.printStackTrace();
                }
            }
        });
    }

}
