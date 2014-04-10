/*
 * blue - object composition environment for csound
 * Copyright (C) 2013
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
package blue.ui.core.score.object.actions;

import blue.BlueSystem;
import blue.score.ScoreObject;
import blue.soundObject.SoundObject;
import blue.ui.core.score.ScoreTopComponent;
import blue.ui.core.score.undo.MoveSoundObjectsEdit;
import blue.undo.BlueUndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Blue",
        id = "blue.ui.core.score.actions.ReverseAction")
@ActionRegistration(
        displayName = "#CTL_ReverseAction")
@Messages("CTL_ReverseAction=Re&verse")
@ActionReference(path = "blue/score/actions", position = 90)
public final class ReverseAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        //FIXME - should be a ContextAwareAction...
        Lookup lkp = ScoreTopComponent.findInstance().getLookup();
        Collection<? extends ScoreObject> selected = lkp.lookupAll(
                ScoreObject.class);

        if (selected.size() < 2) {
            return;
        }

        float start = Float.MAX_VALUE;
        float end = Float.MIN_VALUE;

        for (ScoreObject scoreObject : selected) {
            float tempStart = scoreObject.getStartTime();
            float tempEnd = tempStart + scoreObject.getSubjectiveDuration();

            if (tempStart < start) {
                start = tempStart;
            }

            if (tempEnd > end) {
                end = tempEnd;
            }
        }

        for (ScoreObject scoreObject : selected) {
            float tempStart = scoreObject.getStartTime();
            float tempEnd = tempStart + scoreObject.getSubjectiveDuration();

            float newStart = start + (end - tempEnd);

            scoreObject.setStartTime(newStart);
        }

        BlueUndoManager.setUndoManager("score");

// FIXME - need to do undoable edit here!

//        MoveSoundObjectsEdit edit = sCanvas.mBuffer.getMoveEdit(
//                sCanvas.getPolyObject());
//
//        edit.setPresentationName(BlueSystem.getString(
//                "soundObjectPopup.reverse.text"));

//        BlueUndoManager.addEdit(edit);
    }
}
