package org.openRealmOfStars.utilities;

/**
*
* Open Realm of Stars game project
* Copyright (C) 2017  Tuomo Untinen
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
* Text Utilities
*
*/
public final class TextUtilities {

  /**
   * Hiding the constructor
   */
  private TextUtilities() {
    // Nothing to do
  }

  /**
   * Get integer as ordering text
   * @param number Integer to convert ordering text
   * @return Ordering text
   * @throws IllegalArgument if integer is below one
   */
  public static String getOrderNumberAsText(final int number) {
    if (number < 1) {
      throw new IllegalArgumentException("Integers below one are not"
          + " allowed!");
    }
    
    switch (number) {
      case 1:
      return "first";
      case 2:
      return "second";
      case 3:
      return "third";
      case 4:
      return "fourth";
      case 11:
      return "11th";
      case 12:
      return "12th";
      case 13:
      return "13th";
      default:
      String temp = Integer.toString(number);
      char lastCh = temp.charAt(temp.length() - 1);
      char secondLastCh = '0';
      if (temp.length() > 2) {
        secondLastCh = temp.charAt(temp.length() - 2);
      }
      if (lastCh == '1' && secondLastCh != '1') {
        return temp + "st";
      } else if (lastCh == '2' && secondLastCh != '1') {
        return temp + "nd";
      } else if (lastCh == '3' && secondLastCh != '1') {
        return temp + "rd";
      } else {
        return temp + "th";
      }
    }
  }
}
