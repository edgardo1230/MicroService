package com.nearshoretechnology.focalpoint.common.util;

import java.awt.Color;

public class ColorUtils {

  /**
   * Calculate contrast text color
   * 
   * @see <a href="http://www.w3.org/TR/AERT#color-contrast">Color contrast</a>
   * @param bgColor background color
   */
  public static String getTextColor( String bgColor ) {
    
    Color bg = hex2Rgb( bgColor );
    
    int brightness = Math.round( ( (bg.getRed() * 299) + (bg.getGreen() * 587) + (bg.getBlue() * 114) ) / 1000 );
  
    return brightness > 125
         ? "#000"
         : "#FFF";
    
  }
  
  /**
   * Hex to RGB
   * 
   * @param colorStr e.g. "#FFFFFF"
   * @return 
   */
  public static Color hex2Rgb( String colorStr ) {
    
      return new Color(
              Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
              Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
              Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
      
  }
  
  /**
   * RGB to Hex
   * 
   * @param color (RGB)
   * @return hex string
   */
  public static String rgb2Hex( Color color ) {
    
    return "#" + 
           toBrowserHexValue( color.getRed() ) + 
           toBrowserHexValue( color.getGreen() ) + 
           toBrowserHexValue( color.getBlue() );
    
  }
  
  /**
   * Integer value to HEX string
   * 
   * @param number
   * @return
   */
  private static String toBrowserHexValue(int number) {
    
    StringBuilder builder = new StringBuilder( Integer.toHexString(number & 0xff) );
    
    while (builder.length() < 2) {
      
      builder.append("0");
      
    }
    
    return builder.toString().toUpperCase();
    
  }
  
}
