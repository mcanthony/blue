/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blue.application;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import org.openide.awt.HtmlBrowser.URLDisplayer;
import org.openide.util.Exceptions;

public final class MakeDonationAction implements ActionListener {

    static final String URL_DONATIONS = "http://www.kunstmusik.com/donations";

    public void actionPerformed(ActionEvent e) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(URL_DONATIONS));
            } else {
                URLDisplayer.getDefault().showURL(new URL(URL_DONATIONS));
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
