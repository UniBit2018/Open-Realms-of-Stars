package org.openRealmOfStars.game.States;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;

import org.openRealmOfStars.game.GameCommands;
import org.openRealmOfStars.gui.BlackPanel;
import org.openRealmOfStars.gui.GuiStatics;
import org.openRealmOfStars.gui.ListRenderers.ProductionListRenderer;
import org.openRealmOfStars.gui.ListRenderers.ShipHullListRenderer;
import org.openRealmOfStars.gui.borders.SimpleBorder;
import org.openRealmOfStars.gui.buttons.SpaceButton;
import org.openRealmOfStars.gui.infopanel.InfoPanel;
import org.openRealmOfStars.player.PlayerInfo;
import org.openRealmOfStars.player.ship.ShipDesign;
import org.openRealmOfStars.player.ship.ShipHull;
import org.openRealmOfStars.player.ship.ShipHullFactory;
import org.openRealmOfStars.player.tech.Tech;
import org.openRealmOfStars.player.tech.TechType;

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
 * Ship Design view for design new ship designs
 * 
 */

public class ShipDesignView extends BlackPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Player Info
   */
  private PlayerInfo player;

  /**
   * Current ship Design
   */
  private ShipDesign design;

  /**
   * ComboBox where to select hull
   */
  private JComboBox<ShipHull> hullSelect;
  
  public ShipDesignView(PlayerInfo player, ShipDesign oldDesign,
      ActionListener listener) {
    this.player = player;
    if (oldDesign != null) {
      design = oldDesign;
    } else {
      design = new ShipDesign(ShipHullFactory.createByName("Colony", player.getRace()));
    }
    this.setLayout(new BorderLayout());
    InfoPanel base = new InfoPanel();
    base.setLayout(new BorderLayout());
    base.setTitle("Ship design");
    
    
    InfoPanel hullPanel = new InfoPanel();
    hullPanel.setLayout(new BoxLayout(hullPanel, BoxLayout.X_AXIS));
    hullPanel.setTitle("Ship's hull");
    
    Tech[] hullTech = this.player.getTechList().getListForType(TechType.Hulls);
    ShipHull[] hulls  = new ShipHull[hullTech.length];
    for (int i = 0;i<hulls.length;i++) {
      hulls[i] = ShipHullFactory.createByName(hullTech[i].getHull(), this.player.getRace());
    }
    
    hullSelect = new JComboBox<>(hulls);
    hullSelect.addActionListener(listener);
    hullSelect.setActionCommand(GameCommands.COMMAND_PRODUCTION_LIST);
    hullSelect.setBackground(GuiStatics.COLOR_COOL_SPACE_BLUE_DARK);
    hullSelect.setForeground(GuiStatics.COLOR_COOL_SPACE_BLUE);
    hullSelect.setBorder(new SimpleBorder());
    hullSelect.setFont(GuiStatics.getFontCubellan());
    hullSelect.setRenderer(new ShipHullListRenderer());
    
    hullPanel.add(hullSelect);
    base.add(hullPanel,BorderLayout.NORTH);


    this.add(base,BorderLayout.CENTER);

    // Bottom panel
    InfoPanel bottomPanel = new InfoPanel();
    bottomPanel.setLayout(new GridLayout(1, 2));
    bottomPanel.setTitle(null);
    SpaceButton btn = new SpaceButton("Cancel",GameCommands.COMMAND_SHIPS);
    btn.addActionListener(listener);
    bottomPanel.add(btn);
    
    btn = new SpaceButton("Accept design", GameCommands.COMMAND_SHIPS);
    btn.addActionListener(listener);
    bottomPanel.add(btn);
    
    //updatePanel();
    // Add panels to base
    this.add(bottomPanel,BorderLayout.SOUTH);

  }
}