package blue.noteProcessor;

import blue.BlueSystem;
import blue.soundObject.Note;
import blue.soundObject.NoteList;
import blue.soundObject.NoteParseException;
import electric.xml.Element;

/**
 * Title: blue Description: an object composition environment for csound
 * Copyright: Copyright (c) 2001 Company: steven yi music
 * 
 * @author steven yi
 * @version 1.0
 */

public class PchInversionProcessor implements NoteProcessor,
        java.io.Serializable {

    float value = 8.00f;

    int pfield = 4;

    public PchInversionProcessor() {
    }

    public String toString() {
        // return "[add] pfield: " + pfield + " value: " + value;
        return "[pch inversion]";
    }

    public String getPfield() {
        return Integer.toString(pfield);
    }

    public void setPfield(String pfield) {
        this.pfield = Integer.parseInt(pfield);
    }

    public String getVal() {
        return Float.toString(value);
    }

    public void setVal(String value) {
        this.value = Float.parseFloat(value);
    }

    public final void processNotes(NoteList in) throws NoteProcessorException {
        Note temp;
        String val;
        for (int i = 0; i < in.size(); i++) {
            temp = in.getNote(i);

            try {
                val = temp.getPField(pfield).trim();
                Float.parseFloat(val);
            } catch (NumberFormatException ex) {
                throw new NoteProcessorException(this, BlueSystem
                        .getString("noteProcessorException.pfieldNotFloat"),
                        pfield);
            } catch (Exception ex) {
                throw new NoteProcessorException(this, BlueSystem
                        .getString("noteProcessorException.missingPfield"),
                        pfield);
            }

            float baseTen = blue.utility.ScoreUtilities.getBaseTen(val);
            float baseTenAxis = blue.utility.ScoreUtilities.getBaseTen(this
                    .getVal());

            float addVal = -1 * (baseTen - baseTenAxis);

            baseTen = baseTenAxis + addVal;

            int octave = (int) (baseTen / 12);
            float strPch = (baseTen % 12) / 100;

            temp.setPField(Float.toString(octave + strPch), pfield);
        }
    }

    public static void main(String args[]) {
        NoteList n = new NoteList();

        for (int i = 0; i < 10; i++) {
            try {
                n.addNote(Note.createNote("i1 " + i + " 2 6.0" + i + " 4"));
            } catch (NoteParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("before: \n\n" + n + "\n\n");

        PchInversionProcessor ap = new PchInversionProcessor();
        ap.setPfield("4");
        ap.setVal("5.00");
        try {
            ap.processNotes(n);
        } catch (NoteProcessorException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        System.out.println("after: \n\n" + n + "\n\n");
    }

    public static NoteProcessor loadFromXML(Element data) {
        PchInversionProcessor pip = new PchInversionProcessor();

        pip.setPfield(data.getElement("pfield").getTextString());
        pip.setVal(data.getElement("value").getTextString());

        return pip;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blue.noteProcessor.NoteProcessor#saveAsXML()
     */
    public Element saveAsXML() {
        Element retVal = new Element("noteProcessor");
        retVal.setAttribute("type", this.getClass().getName());

        retVal.addElement("pfield").setText(this.getPfield());
        retVal.addElement("value").setText(this.getVal());

        return retVal;
    }
}
