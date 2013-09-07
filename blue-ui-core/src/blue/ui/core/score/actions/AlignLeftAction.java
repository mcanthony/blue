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
package blue.ui.core.score.actions;

import blue.soundObject.SoundObject;
import java.awt.event.ActionEvent;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

//@ActionID(
//        category = "ScoreObject",
//        id = "blue.ui.core.score.actions.AlignLeftAction")
//@ActionRegistration(
//        displayName = "#CTL_AlignLeftAction")
@Messages("CTL_AlignLeftAction=Align Left")
//@ActionReference(path = "blue/score/actions/Align", position = 100)
public final class AlignLeftAction extends AbstractAction implements ContextAwareAction {

    private final Collection<? extends SoundObject> soundObjects;

    public AlignLeftAction() {
        this(null);
    }

    public AlignLeftAction(Collection<? extends SoundObject> soundObjects) {
        super(NbBundle.getMessage(AlignRightAction.class, "CTL_AlignLeftAction"));
        this.soundObjects = soundObjects;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Collection<? extends SoundObject> soundObjects =
                Utilities.actionsGlobalContext().lookupAll(SoundObject.class);

        if (soundObjects.size() < 2) {
            return;
        }

        // FIXME
//        float initialStartTimes[] = new float[sCanvas.mBuffer.size()];
//        float endingStartTimes[] = new float[sCanvas.mBuffer.size()];
//
//        float farLeft = soundObjects[0].getStartTime();
//        initialStartTimes[0] = farLeft;
//
//        float tempStart;
//        for (int i = 1; i < soundObjects.length; i++) {
//            SoundObject sObj = soundObjects[i];
//            tempStart = sObj.getStartTime();
//
//            initialStartTimes[i] = tempStart;
//
//            if (tempStart < farLeft) {
//                farLeft = tempStart;
//            }
//        }
//        for (int i = 0; i < soundObjects.length; i++) {
//            SoundObject sObj = soundObjects[i];
//            sObj.setStartTime(farLeft);
//            endingStartTimes[i] = farLeft;
//        }
//
//        BlueUndoManager.setUndoManager("score");
//        AlignEdit edit = new AlignEdit(soundObjects, initialStartTimes,
//                endingStartTimes);
//
//        edit.setPresentationName("Align Left");
//
//        BlueUndoManager.addEdit(edit)
    }

    @Override
    public boolean isEnabled() {
        return soundObjects.size() > 1;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext) {
        return new AlignLeftAction(actionContext.lookupAll(SoundObject.class));
    }
}