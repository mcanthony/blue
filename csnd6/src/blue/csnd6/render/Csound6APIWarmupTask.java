/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blue.csnd6.render;

import csnd6.Csound;
import csnd6.csnd6;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.Exceptions;

/**
 *
 * @author stevenyi
 */
public class Csound6APIWarmupTask implements Runnable {

    @Override
    public void run() {

        if (!API6Utilities.isCsoundAPIAvailable()) {
            return;
        }

        Logger.getLogger("Csound6APIWarmupTask").log(Level.INFO,
                "Warming up Csound 6 API");

        if (API6Utilities.isCsoundAPIAvailable()) {

            csnd6.csoundInitialize(csnd6.CSOUNDINIT_NO_SIGNAL_HANDLER | 
                    csnd6.CSOUNDINIT_NO_ATEXIT);

            Csound csound = new Csound();
            File f, f2;
            try {

                StringBuilder csd = new StringBuilder();
                csd.append("<CsoundSynthesizer>\n");
                csd.append("<CsInstruments>\n");
                csd.append("sr=44100\nksmps=64\nnchnls=2\n");
                csd.append("instr 1\nendin\n");
                csd.append("</CsInstruments>\n");
                csd.append("<CsScore>\ni1 0 .01\n</CsScore>\n");
                csd.append("</CsoundSynthesizer>\n");

                f = File.createTempFile("dummy", ".csd");
                f2 = File.createTempFile("dummy", ".wav");

                FileOutputStream fos = new FileOutputStream(f);
                fos.write(csd.toString().getBytes());

                if (csound.Compile(f.getAbsolutePath(), "-o",
                        f2.getAbsolutePath()) == 0) {
                    csound.Perform();
                }
                csound.Reset();

                if (f.exists()) {
                    f.delete();
                }

                if (f2.exists()) {
                    f2.delete();
                }

            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        }
    }
}
