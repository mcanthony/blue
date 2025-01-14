/*
 * blue - object composition environment for csound
 * Copyright (C) 2012
 * Steven Yi <stevenyi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package blue.score;

import blue.utility.XMLUtilities;
import electric.xml.Element;
import electric.xml.Elements;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author stevenyi
 */
public class TimeState implements Serializable, Cloneable {

    public static final int DISPLAY_TIME = 0;
    public static final int DISPLAY_NUMBER = 1;
    
    private transient Vector<PropertyChangeListener> listeners = null;

    private int pixelSecond = 64;
    private boolean snapEnabled = false;
    private float snapValue = 1.0f;
    private int timeDisplay = DISPLAY_TIME;
    private int timeUnit = 5;

    public int getPixelSecond() {
        return this.pixelSecond;
    }

    public void setPixelSecond(int pixelSecond) {
        PropertyChangeEvent pce = new PropertyChangeEvent(this, "pixelSecond",
                new Integer(this.pixelSecond), new Integer(pixelSecond));

        this.pixelSecond = pixelSecond;

        firePropertyChangeEvent(pce);
    }
    
    public void lowerPixelSecond() {
        int temp = getPixelSecond();

        if (temp <= 2) {
            return;
        }

        temp -= 2;

        setPixelSecond(temp);
    }

    public void raisePixelSecond() {
        int temp = getPixelSecond() + 2;
        setPixelSecond(temp);
    }

    public boolean isSnapEnabled() {
        return this.snapEnabled;
    }

    public void setSnapEnabled(boolean snapEnabled) {
        PropertyChangeEvent pce = new PropertyChangeEvent(this, "snapEnabled",
                Boolean.valueOf(this.snapEnabled), Boolean.valueOf(snapEnabled));

        this.snapEnabled = snapEnabled;

        firePropertyChangeEvent(pce);
    }

    public float getSnapValue() {
        return this.snapValue;
    }

    public void setSnapValue(float snapValue) {
        PropertyChangeEvent pce = new PropertyChangeEvent(this, "snapValue",
                new Float(this.snapValue), new Float(snapValue));

        this.snapValue = snapValue;

        firePropertyChangeEvent(pce);
    }

    public int getTimeDisplay() {
        return timeDisplay;
    }

    public void setTimeDisplay(int timeDisplay) {
        PropertyChangeEvent pce = new PropertyChangeEvent(this, "timeDisplay",
                new Integer(this.timeDisplay), new Integer(timeDisplay));

        this.timeDisplay = timeDisplay;

        firePropertyChangeEvent(pce);
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        PropertyChangeEvent pce = new PropertyChangeEvent(this, "timeUnit",
                new Integer(this.timeUnit), new Integer(timeUnit));

        this.timeUnit = timeUnit;

        firePropertyChangeEvent(pce);
    }
    
    /* PROPERTY CHANGE LISTENER CODE */
    
    private void firePropertyChangeEvent(PropertyChangeEvent pce) {
        if (listeners == null) {
            return;
        }

        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(pce);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        if (listeners == null) {
            listeners = new Vector<PropertyChangeListener>();
        }

        listeners.add(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        if (listeners == null) {
            return;
        }
        listeners.remove(pcl);
    }

    /*
     * SERIALIZATION CODE
     */
    public static TimeState loadFromXML(Element data) {
        
        TimeState timeState = new TimeState();
        
        Elements nodes = data.getElements();

        while (nodes.hasMoreElements()) {
            Element e = nodes.next();

            String nodeName = e.getName();
            final String nodeText = e.getTextString();

            if (nodeName.equals("pixelSecond")) {
                timeState.pixelSecond = Integer.parseInt(nodeText);
            } else if (nodeName.equals("snapEnabled")) {
                timeState.snapEnabled = Boolean.valueOf(nodeText).
                        booleanValue();
            } else if (nodeName.equals("snapValue")) {
                timeState.snapValue = Float.parseFloat(nodeText);
            } else if (nodeName.equals("timeDisplay")) {
                timeState.timeDisplay = Integer.parseInt(nodeText);
            } else if (nodeName.equals("timeUnit")) {
                timeState.timeUnit = Integer.parseInt(nodeText);
            }
        }
        
        return timeState;
    }

    public Element saveAsXML() {
        Element retVal = new Element("timeState");
        
        retVal.addElement(XMLUtilities.writeInt("pixelSecond", 
                this.pixelSecond));
        retVal.addElement(XMLUtilities.writeBoolean("snapEnabled", 
                this.snapEnabled));
        retVal.addElement(XMLUtilities.writeFloat("snapValue", this.snapValue));
        retVal.addElement(XMLUtilities.writeInt("timeDisplay", this.timeDisplay));
        retVal.addElement(XMLUtilities.writeInt("timeUnit", this.timeUnit));

        return retVal;
    }

    @Override
    public Object clone() {
        TimeState timeState = new TimeState();
        timeState.pixelSecond = this.pixelSecond;
        timeState.snapEnabled = this.snapEnabled;
        timeState.snapValue = this.snapValue;
        timeState.timeDisplay = this.timeDisplay;
        timeState.timeUnit = this.timeUnit;
        
        return timeState;
    }
    
    
}
