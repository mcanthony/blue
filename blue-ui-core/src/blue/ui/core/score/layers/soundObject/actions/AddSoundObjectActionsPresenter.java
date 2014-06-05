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
package blue.ui.core.score.layers.soundObject.actions;

import blue.BlueSystem;
import blue.score.TimeState;
import blue.soundObject.PolyObject;
import blue.soundObject.SoundObject;
import blue.ui.core.score.ScoreTopComponent;
import blue.ui.core.score.layers.soundObject.ScoreTimeCanvas;
import blue.ui.core.score.undo.AddSoundObjectEdit;
import blue.undo.BlueUndoManager;
import blue.utility.ScoreUtilities;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;
import org.openide.windows.WindowManager;

@ActionID(
        category = "Blue",
        id = "blue.ui.core.score.layers.soundObject.actions.AddSoundObjectActionsPresenter")
@ActionRegistration(
        displayName = "#CTL_AddSoundObjectActionsPresenter")
@Messages("CTL_AddSoundObjectActionsPresenter=Add &SoundObject")
@ActionReference(path = "blue/score/layers/soundObject/actions",
        position = 40, separatorAfter = 45)
public final class AddSoundObjectActionsPresenter extends AbstractAction implements
        ContextAwareAction, Presenter.Popup {

    Map<String, Class> sObjNameClassMap = new HashMap<>();
    JMenu menu = null;
    WeakReference<Point> pRef = null;
    WeakReference<ScoreTimeCanvas> sTimeCanvasRef = null;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (pRef == null || sTimeCanvasRef == null) {
            return;
        }

        ScoreTimeCanvas sTimeCanvas = sTimeCanvasRef.get();
        Point p = pRef.get();
        int sLayerIndex = sTimeCanvas.getPolyObject().getLayerNumForY(
                (int) p.getY());

        ScoreTopComponent stc = (ScoreTopComponent) WindowManager.getDefault().
                findTopComponent("ScoreTopComponent");

        try {

            Class c = (Class) ((JMenuItem) e.getSource()).getClientProperty(
                    "sObjClass");
            SoundObject sObj = (SoundObject) c.newInstance();

            if(sObj instanceof PolyObject) {
                ((PolyObject)sObj).newLayerAt(0);
            }
            
            TimeState timeState = stc.getTimeState();

            float start = (float) p.getX() / timeState.getPixelSecond();

            if (timeState.isSnapEnabled()) {
                start = ScoreUtilities.getSnapValueStart(start,
                        timeState.getSnapValue());
            }
            sObj.setStartTime(start);

            sTimeCanvas.getPolyObject().addSoundObject(sLayerIndex, sObj);

            BlueUndoManager.setUndoManager("score");
            BlueUndoManager.addEdit(new AddSoundObjectEdit(
                    sTimeCanvas.getPolyObject(), sObj, sLayerIndex));

        } catch (InstantiationException | IllegalAccessException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public JMenuItem getPopupPresenter() {
        if (menu == null) {
            menu = new JMenu("Add SoundObject");

            FileObject sObjFiles[] = FileUtil.getConfigFile(
                    "blue/score/soundObjects").getChildren();
            List<FileObject> orderedSObjFiles = FileUtil.getOrder(
                    Arrays.asList(sObjFiles), true);

            for (FileObject fObj : orderedSObjFiles) {

                if(fObj.isFolder()) {
                    continue;
                }
                
                String displayName = (String) fObj.getAttribute(("displayName"));

                SoundObject sObj = FileUtil.getConfigObject(fObj.getPath(),
                        SoundObject.class);

//                if ("PolyObject".equals(displayName)) {
//                    continue;
//                }

                JMenuItem temp = new JMenuItem();
                temp.setText(
                        BlueSystem.getString("soundLayerPopup.addNew") + " "
                        + displayName);
                temp.putClientProperty("sObjClass", sObj.getClass());
                temp.setActionCommand(displayName);
                temp.addActionListener(this);
                menu.add(temp);
            }
        }

        return menu;
    }

    @Override
    public Action createContextAwareInstance(Lookup actionContext
    ) {
        pRef = new WeakReference<>(actionContext.lookup(Point.class));
        sTimeCanvasRef = new WeakReference<>(actionContext.lookup(
                ScoreTimeCanvas.class));
        return this;
    }
}
