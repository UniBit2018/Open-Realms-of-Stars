package org.openRealmOfStars.game.States;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.openRealmOfStars.game.GameCommands;
import org.openRealmOfStars.gui.BlackPanel;
import org.openRealmOfStars.gui.GuiStatics;
import org.openRealmOfStars.gui.ListRenderers.ShipStatRenderer;
import org.openRealmOfStars.gui.buttons.SpaceButton;
import org.openRealmOfStars.gui.infopanel.InfoPanel;
import org.openRealmOfStars.gui.labels.InfoTextArea;
import org.openRealmOfStars.gui.panels.InvisiblePanel;
import org.openRealmOfStars.player.PlayerInfo;
import org.openRealmOfStars.player.ship.ShipStat;

/**
 * 
 * Open Realm of Stars game project
 * Copyright (C) 2016  Tuomo Untinen
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
 * along with this program; if not, see http://www.gnu.org/licenses/
 * 
 * 
 * Ship view for showing ship design and stats
 * 
 */

public class ShipView extends BlackPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Player Info
   */
  private PlayerInfo player;
  
  /**
   * List of ship design
   */
  private JList<ShipStat> shipList;
  
  /**
   * Text is containing information about the ship design and stats
   */
  private InfoTextArea infoText;

  public ShipView(PlayerInfo player, ActionListener listener) {
    this.player = player;
    this.setLayout(new BorderLayout());
    InfoPanel base = new InfoPanel();
    base.setLayout(new BorderLayout());
    base.setTitle("Ships");
    shipList = new JList<>();
    shipList.setCellRenderer(new ShipStatRenderer());
    shipList.setListData(this.player.getShipStatList());
    shipList.setBackground(Color.BLACK);
    shipList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scroll = new JScrollPane(shipList);
    InvisiblePanel invis = new InvisiblePanel(base);
    invis.setLayout(new BoxLayout(invis, BoxLayout.Y_AXIS));
    invis.add(scroll);
    SpaceButton btn = new SpaceButton("Copy design", GameCommands.COMMAND_COPY_SHIP);
    btn.addActionListener(listener);
    invis.add(btn);
    btn = new SpaceButton("New design", GameCommands.COMMAND_SHIPDESIGN);
    btn.addActionListener(listener);
    invis.add(btn);
    base.add(invis,BorderLayout.WEST);

    infoText = new InfoTextArea(30, 30);
    infoText.setEditable(false);
    infoText.setFont(GuiStatics.getFontCubellanSmaller());
    scroll = new JScrollPane(infoText);
    base.add(scroll,BorderLayout.CENTER);

    
    this.add(base, BorderLayout.CENTER);
    

    // Bottom panel
    InfoPanel bottomPanel = new InfoPanel();
    bottomPanel.setLayout(new BorderLayout());
    bottomPanel.setTitle(null);
    btn = new SpaceButton("Back to star map", 
        GameCommands.COMMAND_VIEW_STARMAP);
    btn.addActionListener(listener);
    bottomPanel.add(btn,BorderLayout.CENTER);
    
    //updatePanel();
    // Add panels to base
    this.add(bottomPanel,BorderLayout.SOUTH);

  }

  /**
   * Handle actions for ship view.
   * @param arg0 ActionEvent command what player did
   */
  public void handleAction(ActionEvent arg0) {
    if (arg0.getActionCommand().equals(GameCommands.COMMAND_ANIMATION_TIMER)) {
      if (shipList.getSelectedIndex() != -1) {
        ShipStat stat = shipList.getSelectedValue();
        infoText.setText(stat.toString());
        this.repaint();
      } else {
        infoText.setText("");
        this.repaint();
      }
    }
  }
 
}
