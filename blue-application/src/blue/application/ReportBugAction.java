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

public final class ReportBugAction implements ActionListener {

    static final String URL_BUGS = "http://sourceforge.net/tracker/?group_id=74382&atid=540830";

    public void actionPerformed(ActionEvent e) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(URL_BUGS));
            } else {
                URLDisplayer.getDefault().showURL(new URL(URL_BUGS));
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
