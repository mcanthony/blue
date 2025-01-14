/*
 * blue - object composition environment for csound
 * Copyright (c) 2000-2004 Steven Yi (stevenyi@gmail.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by  the Free Software Foundation; either version 2 of the License or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307 USA
 */
package blue.ui.core.mixer;

import blue.orchestra.blueSynthBuilder.BSBCheckBox;
import blue.orchestra.blueSynthBuilder.BSBDropdown;
import blue.orchestra.blueSynthBuilder.BSBFileSelector;
import blue.orchestra.blueSynthBuilder.BSBHSlider;
import blue.orchestra.blueSynthBuilder.BSBHSliderBank;
import blue.orchestra.blueSynthBuilder.BSBKnob;
import blue.orchestra.blueSynthBuilder.BSBLabel;
import blue.orchestra.blueSynthBuilder.BSBLineObject;
import blue.orchestra.blueSynthBuilder.BSBObjectEntry;
import blue.orchestra.blueSynthBuilder.BSBTextField;
import blue.orchestra.blueSynthBuilder.BSBVSlider;
import blue.orchestra.blueSynthBuilder.BSBVSliderBank;
import blue.orchestra.blueSynthBuilder.BSBXYController;

/**
 * Registry of available objects for Effects.
 */
public class EffectsObjectRegistry {

    public static BSBObjectEntry[] getBSBObjects() {
        return new BSBObjectEntry[] {
                new BSBObjectEntry("Knob", BSBKnob.class),
                new BSBObjectEntry("Horizontal Slider", BSBHSlider.class),
                new BSBObjectEntry("Horizontal Slider Bank",
                        BSBHSliderBank.class),
                new BSBObjectEntry("Vertical Slider", BSBVSlider.class),
                new BSBObjectEntry("Vertical Slider Bank", BSBVSliderBank.class),
                new BSBObjectEntry("CheckBox", BSBCheckBox.class),
                new BSBObjectEntry("Label", BSBLabel.class),
                // new BSBObjectEntry("Tabbed Pane", BSBTabbedPane.class),
                new BSBObjectEntry("Dropdown List", BSBDropdown.class),
                new BSBObjectEntry("File Selector", BSBFileSelector.class),
                new BSBObjectEntry("XY Controller", BSBXYController.class),
                new BSBObjectEntry("Line Object", BSBLineObject.class),
                new BSBObjectEntry("Text Field", BSBTextField.class) };
    }

}
